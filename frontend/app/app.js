angular
	.module('ngHacks', [
		'ngHacks.channel',
		'firebase',
		'ui.router'
	])
	.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise( '/channel' );
	}])

	.controller('AppCtrl', ['$scope', '$location', function ($scope, $location) {
		$scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){		
			/*
			if ( angular.isDefined( toState.data.pageTitle ) ) {
				$scope.pageTitle = toState.data.pageTitle + ' | Hacks' ;
			}
			*/
		});
	}])


	.constant('DBurl', 'https://brilliant-inferno-1466.firebaseio.com/')
	.constant('DBmarkers', 'https://brilliant-inferno-1466.firebaseio.com/markers')
	.constant('DBevents', 'https://brilliant-inferno-1466.firebaseio.com/events/')


	.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
		$rootScope.$state = $state;
		$rootScope.$stateParams = $stateParams;
		console.log('ngHacks::running');
	}]);
