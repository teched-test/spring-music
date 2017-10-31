angular.module('news', ['ngResource', 'ui.bootstrap']).
    factory('News', function ($resource) {
        return $resource('newsResource');
    }).
    factory('SingleNews', function ($resource) {
        return $resource('newsResource/:id', {id: '@id'});
    });

function NewsController($scope, $modal, News, SingleNews) {
    function listNews() {
        $scope.news = News.query();
    }

    function clone (obj) {
        return JSON.parse(JSON.stringify(obj));
    }
    
    $scope.setNewsView = function (viewName) {
        $scope.newsView = "templates/" + viewName + ".html";
    };

    $scope.initNews = function() {
    	listNews();
        $scope.setNewsView("newsGrid");
    };
}
