angular.module('ui.splash', [
	'ui.bootstrap', 
	'ngAnimate'
])
.service('$splash', ['$uibModal', '$rootScope', function($uibModal, $rootScope) {
	return {
		open: function (attrs, opts) {
			var scope = $rootScope.$new();
			angular.extend(scope, attrs);
			opts = angular.extend(opts || {}, {
				backdrop: false,
				scope: scope,
				templateUrl: 'app/common/splash-content.tpl.html',
				windowTemplateUrl: 'app/common/splash-index.tpl.html'
			});
			return $uibModal.open(opts);
		}
	};
}]);