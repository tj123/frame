

(function ($) {
	
    var grid = function(opt,parm){
        // 默认配置信息
        var deft = {
            type: 'POST' // ajax加载方式
            , pagination: true // 是否分页查询
            , size: [8,10,20] // 每页显示的数量
            , maxPageSize: 500 //不分页时查询的最大数据条数
            , goPageNum: 5 //分页条中快捷定位数字的数量,小于5则不显示
            , queryParams: {} //查询条件
            , sn: true //是否显示序号
            , select: true //是否可选择表格
            , singleSelect: false //是否单选行
            , onTrClick: null//TR点击事件
            , onLoaded: null//列表加載完成事件
        };

        if (!opt) {
            $.error('未指定opts参数！');
            return this;
        }
        //判断函数调用意图
        if (typeof opt === 'string') {
            if ($.isFunction(grid.methods[opt])) {
                return grid.methods[opt](this, parm);
            } else {
                $.error('未定义的函数：' + opt);
                return this;
            }
        } else if (typeof opt === 'object') {
            init(this, $.extend(deft, opt));
        } else {
            $.error('非法的 opts 参数！');
            return this;
        }
        return this;
    };


    //初始化Grid，检查配置信息
    var init = function (jq, opt) {
        if (!opt.url) {
            $.error('初始化失败，未指定url参数！');
            return;
        }
        if (!opt.columns || opt.columns.length == 0) {
            $.error('初始化失败，未指定columns参数！');
            return;
        }
        if (jq.length == 0) {
            $.error('初始化失败，没有找到table元素！');
            return;
        }

        jq.empty();
        var table = $('<table></table>').appendTo(jq);
        var head = $('<thead></thead>').appendTo(table);
        for(var i in cs = opt.columns){
            var th = $('<th>'+cs[i].title+'</th>').appendTo(head);
            if(cs[i].field == 'sn')
                th.css('width','40px');
            if(cs[i].width)
                th.css('width',cs[i].width.toString().match(/\d+/).toString()+'px');
        }
        var body = $('<tbody></tbody>').appendTo(table);
        var foot = $('<div class="foot"></div>').appendTo(jq);
        var size = opt.size;
        if(typeof opt.size === 'object'){
            foot.append('<span class="count-tip">条数</span>');
            if(opt.size.length == 0 ){
                $.error('size 错误');
                return;
            }
            size = opt.size[0];
            var html = '';
            for(var i in opt.size){
                html += '<option value="'+opt.size[i]+'">'+opt.size[i]+'</option>';
            }
            var select = $('<select class="count"></select>');
            $(html).appendTo(select);
            select.appendTo(foot);
            var pages = $('<div class="pages"></div>');
            pages.appendTo(foot);
            select.on('change',function(){
                var index = jq.data('index'),
                    val = $(this).val(),
                    pg = 1;
                if(index){
                    pg = parseInt(Math.ceil(index / val));
                }
                jq.data('size',val);
                load(jq,pg);
            });
        }else{
            var pages = $('<div class="pages"></div>');
            pages.appendTo(foot);
        }
        if (jq.data('opt')) {
            jq.removeData('opt');
        }
        jq.data('opt', opt);
        jq.data('size',size);
        load(jq,1);
        jq.data('hasInit', true);
    };

    /**
     * 服务器加载数据
     */
    var load = function(jq,page,keywords){
        page = parseInt(page);
        if(page < 1) page = 1;
        var opt = jq.data('opt'),size = jq.data('size');
        if(jq.data('isLoading') === true){
            setTimeout(function(){
                jq.data('isLoading',false);
            },5000)
            return;
        }
        jq.data('isLoading',true);
        jq.data('page',page);
        var kw = {};
        if(!keywords){
            var jqk = jq.data('keyword');
            if(jqk){
                kw = jqk;
            }else if(opt.search){
                if($.isArray(opt.search)){
                    for(var i in opt.search){
                        var ipt = $(opt.search[i]);
                        kw[ipt.attr('name')] = ipt.val().trim();
                    }
                }else{
                    kw = $(opt.search).val().trim();
                }
            }
        }else{
            if(typeof keywords === 'object'){
                for(var k in keywords){
                    kw[k] = keywords[k].trim();
                }
            }else{
                kw = keywords.trim();
            }
            jq.data('keyword',keywords);
        }
        loading(jq,opt.columns.length,size * 40);
        sd.ajax({
            url:opt.url,
            data: {
                page:page,
                size:size,
                keyword:(typeof kw === 'object')?'':kw,
                keywords:(typeof kw === 'object')?kw:{}
            },
            success:function(dat){
                var body = jq.find('table tbody'),
                    pg = page,
                    rws = dat.rows,
                    clms = opt.columns,
                    sn = opt.sn,
                    trck = opt.onTrClick,
                	onloadeds = opt.onLoaded;//列表刷新执行完后回调函数
                body.empty();
                if($.isFunction(trck)){
                    body.addClass('tr-link');
                }
                for(var i in rws){
                    var tr =$('<tr class="'+(((i%2)==0)?'odd':'even')+'"></tr>').appendTo(body);
                    if($.isFunction(trck)){
                        tr.on('click',function(){
                            trck($(this).data('guid'),this);
                        }).data('guid',rws[i].guid);
                    }
                    tr.data('row',rws[i]);
                    if(i == 0){
                        jq.data('index',size*(pg-1)+parseInt(i)+1);
                    }
                    if(sn){
                        tr.append('<td>'+(size*(pg-1)+parseInt(i)+1)+'</td>');
                    }
                    for(var ci in clms){
                        var fd = clms[ci].field, ttl = clms[ci].title,lk = clms[ci].link,rep = clms[ci].replace,
                            al = clms[ci].align,mch = clms[ci].match,relti=clms[ci].reltitle;
                        if( fd == 'sn') continue;
                        if(!lk){
                            var td;
                            if($.isFunction(rep)){
                                if(mch === false){
                                    td = $('<td>'+rep(rws[i][fd])+'</td>').appendTo(tr);
                                }else{
                                    td = $('<td>'+sd.match(rep(rws[i][fd]),kw)+'</td>').appendTo(tr);
                                }
                            }else{
                                if(mch === false){
                                    td = $('<td>'+rws[i][fd]+'</td>').appendTo(tr);
                                }else{
                                    td = $('<td>'+sd.match(rws[i][fd],kw)+'</td>').appendTo(tr);
                                }
                            }
                            if(al == 'right' || al == 'left'){
                                td.css('text-align',al);
                            }
                            if( relti == false){

                            }else{
                                td.attr('title',rws[i][fd]);
                            }
                        }else{
                            var ope = $('<td></td>').appendTo(tr);
                            for(var l in lk){
                                var n = lk[l].name,o = lk[l].operation,it=lk[l].init;
                                var a = $('<a href="javascript:void(0);"></a>').text(n).appendTo(ope);
                                if($.isFunction(o)){
                                    a.on('click',function(e){
                                        sd.stopBubble(e);
                                        $(this).data('operation')($(this).data('guid'),this);
                                    }).data('guid',rws[i].guid).data('operation',o);
                                }
                                if($.isFunction(it)){
                                    it(a.get(0), a.parent().parent().data('row'));
                                }
                                if(l != lk.length - 1 ){
                                    a.after('<span>|</span>');
                                }
                            }
                        }
                    }
                }
                if(rws.length == 0){
                    body.append('<tr class="none">'
                        +'<td colspan="'+opt.columns.length+'">'
                            +'没有相关结果！'
                        +'</td>'
                    +'</tr>').removeClass('tr-link');
                }
                pages(jq,pg,dat.total);
                jq.data('isLoading',false);
                if ($.isFunction(onloadeds)) {
                	onloadeds(jq);
                }
            }
        });

    };

    var PAGE_DIVIDE = 7;

    /**
     *更新条数显示
     */
    var pages = function(jq,page,total){
        var pg = jq.find('.foot .pages');
        pg.empty();
        var pre = $('<a href="javascript:void(0);" class="previous">&lt;上一页</a>').appendTo(pg);
        if(page == 1){
            pre.addClass('disable');
        }else{
            pre.on('click',function(){
               load(jq,page - 1);
            });
        }
        var show = function(start,stop,p){
            if(stop){
                for(var i = start ;i<=stop;i++){
                    var a = $('<a href="javascript:void(0);" class="page"></a>').text(i).appendTo(pg);
                    if(i != p){
                        a.on('click',function(){
                            load(jq,$(this).data('pg'));
                        }).data('pg',i);
                    }else{
                        a.addClass('active');
                    }
                }
            }else{
                $('<a href="javascript:void(0);" class="page"></a>').text(start).appendTo(pg).on('click',function(){
                    load(jq,$(this).data('pg'));
                }).data('pg',start);
            }
        };
        var showMore = function(){
            $('<span class="more">...</span>').appendTo(pg);
        };
        if( total <= PAGE_DIVIDE + 1 || page <= PAGE_DIVIDE -3){
            if(total > PAGE_DIVIDE +1){
                show(1,PAGE_DIVIDE,page);
                showMore();
                show(total);
            }else{
                show(1,total,page);
            }
        }else if(page > PAGE_DIVIDE-3 && page < total -PAGE_DIVIDE + 3){
            show(1);
            showMore();
            if(page + 3 >= total){
                show(page - 3,total,page);
            }else {
                show(page - 3,page +3,page);
                showMore();
                show(total);
            }
        }else if(page >= total - PAGE_DIVIDE + 3){
            show(1);
            showMore();
            show(total-PAGE_DIVIDE,total,page);
        }
        var nxt = $('<a href="javascript:void(0);" class="next">下一页&gt;</a>').appendTo(pg);
        if(page == total){
            nxt.addClass('disable');
        }else{
            nxt.on('click',function(){
                load(jq,page + 1);
            });
        }

    };

    /**
     * 显示正在加载
     * @param cp colspan
     * @param ht 高度
     */
    var loading = function(jq,cp,ht){
        var body = jq.find('table tbody').empty().removeClass('tr-link'),
            tr = $('<tr class="loading">'
                +'<td colspan="'+cp+'"><div><img src="'+sd.CONTEXT+'/img/loading.gif"></div>'
                +'<div class="tip">正在加载...</div></td>'
            +'</tr>').appendTo(body);
        if(ht){
            tr.css('height',ht + 'px').css('line-height',ht + 'px');
        }
    };

   grid.methods = {
        load:function(jq,page){
            load(jq,page);
        },
        reload:function(jq){
            load(jq,jq.data('page'));
        },
        previous:function(jq){
            load(jq,jq.data('page')-1);
        },
        next:function(jq){
            load(jq,jq.data('page')+1);
        },
        search:function(jq,keywords){
            load(jq,1,keywords);
        },
        searchByInput:function(jq){
            jq.data('keyword',null);
            load(jq);
        }
    };


    $.fn.sdgrid = grid;

})(jQuery);