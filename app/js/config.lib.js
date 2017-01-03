;(function (app) {
  app
  /**
   * jquery 库
   */
    .constant('JQ_LIB', {
      footable: ['js/lib/jquery/footable/footable.all.min.js', 'js/lib/jquery/footable/footable.core.css'],
      nestable: ['js/lib/jquery/nestable/jquery.nestable.js', 'js/lib/jquery/nestable/nestable.css'],
      jstree:['js/lib/jquery/jstree/jstree.js','js/lib/jquery/jstree/themes/default/style.min.css']
    })
    /**
     * angular 模块库
     */
    .constant('MODULE_LIB', [{
      name: 'ngGrid',
      files: ['js/lib/modules/ng-grid/ng-grid.min.js', 'js/lib/modules/ng-grid/ng-grid.min.css', 'js/lib/modules/ng-grid/theme.css']
    }, {
      name: 'ui.select',
      files: ['js/lib/modules/angular-ui-select/select.min.js', 'js/lib/modules/angular-ui-select/select.min.css']
    }, {
      name: 'angularFileUpload',
      files: ['js/lib/modules/angular-file-upload/angular-file-upload.min.js']
    }, {
      name: 'ui.calendar',
      files: ['js/lib/modules/angular-ui-calendar/calendar.js']
    }, {
      name: 'ngImgCrop',
      files: ['js/lib/modules/ngImgCrop/ng-img-crop.js', 'js/lib/modules/ngImgCrop/ng-img-crop.css']
    }, {
      name: 'sd.grid',
      files: ['js/drt/sd-grid.js', 'css/sdgrid.css']
    }])
})(app);


