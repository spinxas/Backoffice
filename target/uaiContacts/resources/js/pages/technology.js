function technologyController($scope, $http) {
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

    $scope.url = "/uaiContacts/protected/technology/";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateTechnologyButton = false;

    $scope.technology = {}

    $scope.searchFor = ""

    $scope.getTechnologyList = function () {
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateTechnologyButton = false;
            });
    }

    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.technology, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalTechnology : data.totalTechnology};

            if($scope.page.pagesCount <= $scope.page.currentPage){
                $scope.pageToGet = $scope.page.pagesCount - 1;
                $scope.page.currentPage = $scope.page.pagesCount - 1;
            }

            $scope.displayCreateTechnologyButton = true;
            $scope.displaySearchButton = true;
        } else {
            $scope.state = 'noresult';
            $scope.displayCreateTechnologyButton = true;

            if(!$scope.searchFor){
                $scope.displaySearchButton = false;
            }
        }

        if (data.actionMessage || data.searchMessage) {
            $scope.displayMessageToUser = $scope.lastAction != 'search';

            $scope.page.actionMessage = data.actionMessage;
            $scope.page.searchMessage = data.searchMessage;
        } else {
            $scope.displayMessageToUser = false;
        }
    }

    $scope.changePage = function (page) {
        $scope.pageToGet = page;

        if($scope.searchFor){
            $scope.searchTechnology($scope.searchFor, true);
        } else{
            $scope.getTechnologyList();
        }
    };

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.technology = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }

    $scope.addSearchParametersIfNeeded = function(config, isPagination) {
        if(!config.params){
            config.params = {};
        }

        config.params.page = $scope.pageToGet;

        if($scope.searchFor){
            config.params.searchFor = $scope.searchFor;
        }
    }

    $scope.resetTechnology = function(){
        $scope.technology = {};
    };

    $scope.createTechnology = function (newTechnologyForm) {
        if (!newTechnologyForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'create';

        var url = $scope.url;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $scope.addSearchParametersIfNeeded(config, false);

        $scope.startDialogAjaxRequest();

        $http.post(url, $.param($scope.technology), config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#addTechnologyModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.selectedTechnology = function (technology) {
        var selectedTechnology = angular.copy(technology);
        $scope.technology = selectedTechnology;
    }

    $scope.updateTechnology = function (updateTechnologyForm) {
        if (!updateTechnologyForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'update';

        var url = $scope.url + $scope.technology.id;

        $scope.startDialogAjaxRequest();

        var config = {}

        $scope.addSearchParametersIfNeeded(config, false);

        $http.put(url, $scope.technology, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#updateTechnologyModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.searchTechnology = function (searchTechnologyForm, isPagination) {
        if (!($scope.searchFor) && (!searchTechnologyForm.$valid)) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'search';

        var url = $scope.url +  $scope.searchFor;

        $scope.startDialogAjaxRequest();

        var config = {};

        if($scope.searchFor){
            $scope.addSearchParametersIfNeeded(config, isPagination);
        }

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#searchTechnologyModal", isPagination);
                $scope.displaySearchMessage = true;
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.deleteTechnology = function () {
        $scope.lastAction = 'delete';

        var url = $scope.url + $scope.technology.id;

        $scope.startDialogAjaxRequest();

        var params = {searchFor: $scope.searchFor, page: $scope.pageToGet};

        $http({
            method: 'DELETE',
            url: url,
            params: params
        }).success(function (data) {
                $scope.resetTechnology();
                $scope.finishAjaxCallOnSuccess(data, "#deleteTechnologyModal", false);
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.resetSearch = function(){
        $scope.searchFor = "";
        $scope.pageToGet = 0;
        $scope.getTechnologyList();
        $scope.displaySearchMessage = false;
    }

    $scope.getTechnologyList();
}
