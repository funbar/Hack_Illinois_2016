angular.module( 'ngHacks.group', [
	'ngHacks',
    'ngMap',
    'ngAnimate',
    'ui.router',
    'ui.bootstrap',
    'anim-in-out'
])

.config(function config( $stateProvider ) {
    $stateProvider
        .state('group', {
        	url: '/group/:groupId',
            controller: 'GroupCtrl',
            templateUrl: 'app/group/group.tpl.html',
            data:{ pageTitle: 'group' }
        });
})

.controller('GroupCtrl', ['$scope', '$state', '$stateParams' , 'DBurl', 'DBevents', 'DBmarkers', '$firebase' ,'$firebaseObject', '$firebaseArray', 'NgMap', '$location', 'icons', 'icons_mk','$uibModal',
	function ($scope, $state, $stateParams, DBurl, DBevents, DBmarkers, $firebase, $firebaseObject, $firebaseArray, NgMap, $location, icons, icons_mk, $uibModal) {	
	
	$scope.icons = icons;
	$scope.icons_mk = icons_mk;
	$scope.groupId = $stateParams.groupId;
    $scope.eventId = "hackillinois";

	var ref_markers = new Firebase(DBmarkers).orderByChild('scope').equalTo('public');
	var ref_event = new Firebase(DBevents + $scope.eventId);
	var ref_roles = new Firebase(DBevents + $scope.eventId + '/roles');
	var ref_group = new Firebase(DBevents + $scope.eventId + '/roles/' + $scope.groupId);

	var ref_group_markers_filtered = new Firebase(DBurl);
	//var private_markers = sync.$asObject();

	var private_markers = {};
	ref_group_markers_filtered.child("events/" + $scope.eventId + "/roles/" + $scope.groupId + "/markers").on('child_added', function(querySnapshot){
		
		var markerKey = querySnapshot.key();
		var markerVal = querySnapshot.val();
		var path = "events/" + $scope.eventId + "/roles/" + $scope.groupId + "/markers/" + markerKey;

		ref_group_markers_filtered.child("markers_proto/" + markerKey).once('value', function(snapshot) {
			private_markers[markerKey] = snapshot.val();
		});
	});

	/*
	ref_group_markers_filtered.child("markers_proto").orderByChild('scope').equalTo('private').on('child_added', function(snapshot) {
		var markerKey = snapshot.key();
		var markerVal = snapshot.val();
		var path = "events/" + $scope.eventId + "/roles/" + $scope.groupId + "/markers/" + markerKey;
		ref_group_markers_filtered.child(path).equalTo(markerKey).once('value', function(snapshot) {
			private_markers[markerKey] = markerVal;
			console.log(markerKey, markerVal, path);
		});
	});
	*/


	$scope.event = $firebaseObject(ref_event);
	$scope.markers = $firebaseArray(ref_markers);
	$scope.markers_pv = private_markers;
	$scope.roles = $firebaseObject(ref_roles);
	$scope.marker_modal = {};

	NgMap.getMap().then(function(map) {		
		$scope.map = map;
	});

	$scope.addMarker = function(e){
		console.log($scope.markers);
		console.log($scope.markers_pv);

		var modalInstance = $uibModal.open({
			animation: true,
			backdrop: false,
			templateUrl: 'app/common/marker-content.tpl.html',
			windowTemplateUrl: 'app/common/marker-index.tpl.html',
			controller: 'NewMarkerModalCtrl',
			resolve: {
				map: function(){ return $scope.map;	},
				latLng: function(){ return e.latLng; },
				icons: function() { return icons; },
				groupId: function(){ return $scope.groupId; },
				eventId: function(){ return $scope.eventId; }
			}
		}); 	
	};


	$scope.navClass = function (page) {
        var currentRoute = $location.path().substring(7) || 'home';
        return page === currentRoute ? 'active' : '';
    }; 
}])

.controller('NewMarkerModalCtrl',
	['$scope', '$uibModalInstance','map', 'latLng', 'icons', 'icons_mk', 'groupId', 'eventId' ,'DBurl','DBevents', 'DBmarkers', '$firebaseObject', '$firebaseArray',
	function($scope, $uibModalInstance, map, latLng, icons, icons_mk, groupId, eventId, DBurl, DBevents, DBmarkers, $firebaseObject, $firebaseArray){

	$scope.lat = latLng.lat();
	$scope.long = latLng.lng();
	$scope.message = "";

	$scope.icons = icons;
	$scope.icons_mk = icons_mk;

	var ref_markers = new Firebase(DBmarkers);
	var ref_group = new Firebase(DBevents + eventId + '/roles/' + groupId);
	var ref_group_markers = new Firebase(DBevents + eventId + '/roles/' + groupId + '/markers');




	$scope.addMarker = function(type) {
		var newChildMarker = ref_markers.push();
		newChildMarker.set({
			title: 'Testing title',
			desc: $scope.message,
			lat: $scope.lat,
			long: $scope.long,
			type: type,
			scope: 'private'
		});

		var newKey = newChildMarker.key();
		ref_group_markers.child(newKey).set(true, function(e){
			$uibModalInstance.dismiss();
		});	


	};

	$scope.createMarker = function(){		
		ref_group.child(name).set({ 
			active: true 
		}, function(e){
			$scope.group_modal.close();
		});	
	};
}])

.run([function () {
	console.log('ngHacks.group::running');
}]);



