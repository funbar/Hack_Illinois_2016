angular.module( 'ngHacks.channel', [
    'ngMap',
    'ngAnimate',
    'ui.router',
    'ui.bootstrap',
    'anim-in-out'
])

.config(function config( $stateProvider ) {
    $stateProvider
        .state( 'channel', {
            url: '/channel',
            controller: 'ChannelCtrl',
            templateUrl: 'app/channel/channel.tpl.html',
            data:{ pageTitle: 'channel' }
        });
})



.controller('ChannelCtrl', ['$scope', '$state', 'icons', 'icons_mk' , 'DBurl', 'DBevents', 'DBmarkers', '$firebaseObject', '$firebaseArray', 'NgMap', '$uibModal',
	function ($scope, $state, icons, icons_mk, DBurl, DBevents, DBmarkers, $firebaseObject, $firebaseArray, NgMap , $uibModal) {	
	
	var eventId = "hackillinois";
	var ref_markers = new Firebase(DBmarkers);
	var ref_event = new Firebase(DBevents + eventId);
	var ref_roles = new Firebase(DBevents + eventId + '/roles');
	



	$scope.icons = icons;
	$scope.icons_mk = icons_mk;
	$scope.event = $firebaseObject(ref_event);
	$scope.markers = $firebaseArray(ref_markers);
	
	$scope.roles = $firebaseObject(ref_roles);
	$scope.group = {};
	$scope.group_modal = {};
	$scope.marker_modal = {};

	NgMap.getMap().then(function(map) {		
		$scope.map = map;
	});


	$scope.newGroup = function () {
		$scope.group = {};
		$scope.error = { message: '' };
		opts = angular.extend($scope, {
			backdrop: false,
			scope: this,
			templateUrl: 'app/common/splash-content.tpl.html',
			windowTemplateUrl: 'app/common/splash-index.tpl.html'
		});
		$scope.group_modal = $uibModal.open(opts);
	};

	$scope.createGroup = function(){
		console.log($scope.group);
		if(typeof $scope.group !== 'undefined' && $scope.group.name && $scope.group.name.length !== 0) {
			var name = $scope.group.name;
			if(name in $scope.roles){
				$scope.error.message = "This group already exists within the channel!";
			}
			else {			
				ref_roles.child(name).set({ 
					active: true 
				}, function(e){
					console.log(e);
					$scope.group_modal.close();
				});				
			}
		}
	};
}])


.run([function () {
	console.log('ngHacks.channel::running');
}]);

