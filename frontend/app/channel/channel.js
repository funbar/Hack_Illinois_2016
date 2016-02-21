angular.module( 'ngHacks.channel', [
    'ngHacks.group',
    'ui.router',
    'ngMap'
])

.config(function config( $stateProvider ) {
    $stateProvider
        .state( 'channel', {
            url: '/channel',
            controller: 'ChannelCtrl',
            templateUrl: 'app/channel/channel.tpl.html',
            data:{ pageTitle: 'channel' }
        })
        .state('channel.group', {
        	url: '/:groupId',
            templateUrl: 'app/group/group.tpl.html',
        	controller: function ($stateParams) {
        		console.log("asdasda");
	        }
        });
})


.controller('ChannelCtrl', ['$scope', '$state', 'DBevents', 'DBmarkers', '$firebaseObject', '$firebaseArray', 'NgMap',
	function ($scope, $state, DBevents, DBmarkers, $firebaseObject, $firebaseArray, NgMap) {	
	
	var eventId = 101;

	var ref_markers = new Firebase(DBmarkers);
	var ref_event = new Firebase(DBevents + eventId);

	$scope.event = $firebaseObject(ref_event);
	$scope.markers = $firebaseArray(ref_markers);

	NgMap.getMap().then(function(map) {		
		$scope.map = map;
	});

	$scope.addMarker = function(e){		
		var marker = new google.maps.Marker({position: e.latLng, map: $scope.map, icon: 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png'});      	
	};

	console.log($scope.event);
	console.log($scope.center);
}])

.run([function () {
	console.log('ngHacks.channel::running');
}]);

