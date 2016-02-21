angular.module( 'ngHacks.group', [
    'ui.router',
    'ngMap'
])

.controller('GroupCtrl', ['$scope', '$state', function ($scope, $state) {	
	
	console.log("test");
}])

.run([function () {
	console.log('ngHacks.group::running');
}]);

