package com.shundian.frame.controller.sys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shundian.frame.api.envm.UploadDirectory;
import com.shundian.lib.Result;
import com.shundian.lib.authorize.Authorize;
import com.shundian.lib.authorize.AuthorizeType;
import com.shundian.lib.util.EnumUtil;
import com.shundian.lib.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件上传下载
 */
@Slf4j
@RestController
@RequestMapping("/sys")
@Authorize(AuthorizeType.ALL)
public class FileController {

    private static final String SUPPORT_FILE_TYPES[] = {".jpg", ".jpeg", ".png", ".gif"};

    @RequestMapping("/to64")
    public void toBase64(MultipartFile multipartFiles) {


    }


    @RequestMapping("/file/upload")
    public void fileUpload(MultipartFile file, String savePath, HttpServletResponse response) {
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        Result<Map<String, Object>> result = new Result<>();
        try {
            Map<String, Object> map = new HashMap<>();
            UploadDirectory uploadDir = EnumUtil.toEnum(UploadDirectory.class, savePath, "getSubDirectory");
            Matcher matcher = Pattern.compile("\\.[a-zA-z0-9]+$").matcher(file.getOriginalFilename());
            String extend = matcher.find() ? matcher.group() : "";
            boolean support = false;
            for (String fileType : SUPPORT_FILE_TYPES) {
                if (fileType.equals(extend)) {
                    support = true;
                    break;
                }
            }
            if (!support) throw new Exception("不支持的文件类型");
            String randomName = UUID.randomUUID().toString().replaceAll("-", "") + extend;
            File saveFile = new File(uploadDir.getDirectory() + File.separator + randomName);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            File canonicalFile = saveFile.getCanonicalFile();
            System.out.println(canonicalFile);

            file.transferTo(saveFile);
            map.put("name", randomName);
            if (log.isDebugEnabled()) {
                log.debug("上传文件:" + file.getOriginalFilename() + " 成功,文件大小:" + StringUtil.toByteFormat(file.getSize()));
            }
            result.ok(map);
        } catch (Exception e) {
            result.error("error", log, e);
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.print(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            log.error("错误", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("错误", e);
                }
            }
        }

    }

    @RequestMapping("/file/del")
    public Result<?> fileDelete(String subPath) {
        Result<?> res = new Result<>();
        try{
            File file = new File(UploadDirectory.BASE_DIRECTORY + File.separator + subPath);
            if (file.exists()) {
                file.delete();
                res.ok();
            }
        }catch (Exception e){
            res.error(log,e);
        }
        return res;
    }

    @RequestMapping("/file/get/{dir}/{name:.+}")
    public void getFile(HttpServletResponse response, @PathVariable String dir, @PathVariable String name) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(UploadDirectory.BASE_DIRECTORY + File.separator + dir + File.separator + name);
            os = response.getOutputStream();
            int i = -1;
            byte[] b = new byte[1024];
            while ((i = is.read(b)) != -1) {
                os.write(b, 0, i);
            }
        } catch (Exception e) {
            log.debug("加载失败", e);
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException ioe) {
                log.debug("加载失败", ioe);
            }
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (Exception e) {
                log.debug("加载失败", e);
            }

        }

    }


}
