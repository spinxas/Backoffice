function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        $scope.activeURL = 'contacts';
    }
    else if($location.$$absUrl.lastIndexOf('/technology') > 0){
        $scope.activeURL = 'technology';
    }
    else
    {
        $scope.activeURL = 'home';
    }
}