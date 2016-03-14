angular
	.module('ngHacks', [
		'ngHacks.channel',
		'ngHacks.group',
		'firebase',
		'ui.router',
		'ngAnimate',
		'anim-in-out'
	])
	.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise( '/channel' );
	}])

	.controller('AppCtrl', ['$scope', '$location', function ($scope, $location) {
		$scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){		
			if ( angular.isDefined( toState.data.pageTitle ) ) {
				$scope.pageTitle = toState.data.pageTitle ;
			}
		});
	}])


	.constant('DBurl', 'https://brilliant-inferno-1466.firebaseio.com/')
	.constant('DBmarkers', 'https://brilliant-inferno-1466.firebaseio.com/markers_proto')
	.constant('DBevents', 'https://brilliant-inferno-1466.firebaseio.com/events/')
	.constant('icons', {
		'0': './assets/icons/hardware.png',
		'1': './assets/icons/redopensource.png',
		'2': './assets/icons/software.png',
		'3': './assets/icons/food.png',
		'4': './assets/icons/stairs.png',
		'5': './assets/icons/restroom.png'
	})

	.constant('icons_mk', {
		'0': './assets/icons/mk_hardware.png',
		'1': './assets/icons/mk_redopensource.png',
		'2': './assets/icons/mk_software.png',
		'3': './assets/icons/mk_food.png',
		'4': './assets/icons/mk_stairs.png',
		'5': './assets/icons/mk_restroom.png'
	})
	
	.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
		$rootScope.$state = $state;
		$rootScope.$stateParams = $stateParams;
		console.log('ngHacks::running');
	}]);

