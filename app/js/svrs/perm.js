;(function (angular, $, app) {

  app.config(['$httpProvider', function ($httpProvider) {

    $httpProvider.interceptors.push(function () {

      return {

        request: function (config) {
          console.log('request');
          console.log(config);
          return config;
        },

        response: function (resp) {
          console.log('response');
          console.log(resp);

          var headers = resp.headers();
          if (/application\/json/i.test(headers['Content-Type'] || headers['content-type'])) {
            var dat = resp.data,$state = app.$state;
            if (dat.errorCode == '901' || dat.errorCode == '903') {
              $state.go('login');
              console.log('u=' + encodeURIComponent(window.location.href));
            } else if (dat.errorCode == '902') {
              $state.go('noperm');
            } else if (dat.errorCode == '908') {
              window.location.hash = '#' + dat.url;
            }
            console.log('data-------------------------------');
            console.log(dat);
          }

          return resp;
        }
      }

    });


  }])


})(angular, jQuery, app);