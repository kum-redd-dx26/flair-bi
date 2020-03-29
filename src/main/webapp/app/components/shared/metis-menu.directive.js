(function () {
    'use strict';

    angular
        .module('flairbiApp')
        .directive('metisMenu', metisMenu);

    metisMenu.$inject = [];

    function metisMenu() {
        // Usage:
        //
        // Creates:
        //
        var directive = {
            bindToController: true,
            controller: MetisMenuController,
            controllerAs: 'vm',
            link: link,
            restrict: 'A',
            scope: {}
        };
        return directive;

        function link(_scope, element, _attrs) {
            $(element).metisMenu();
        }
    }

    MetisMenuController.$inject = [];

    function MetisMenuController() {

    }
})();
