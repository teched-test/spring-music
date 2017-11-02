angular.module('SpringMusic', ['albums','news', 'errors', 'status', 'info', 'ngRoute', 'ui.directives']).
    config(function ($locationProvider, $routeProvider) {
        // $locationProvider.html5Mode(true);

        $routeProvider.when('/news', {
            controller: 'NewsController',
            templateUrl: 'templates/news.html'
        }).when('/errors', {
            controller: 'ErrorsController',
            templateUrl: 'templates/errors.html'
        }).otherwise({
            controller: 'AlbumsController',
            templateUrl: 'templates/albums.html'
        });
    }
);
