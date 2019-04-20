import * as angular from 'angular';
import resetFinishHtml from './reset.finish.html';

'use strict';

angular
    .module('flairbiApp')
    .config(stateConfig);

stateConfig.$inject = ['$stateProvider'];

function stateConfig($stateProvider) {
    $stateProvider.state('finishReset', {
        parent: 'entity',
        url: '/reset/finish?key',
        data: {
            authorities: []
        },
        views: {
            'content@': {
                template: resetFinishHtml,
                controller: 'ResetFinishController',
                controllerAs: 'vm'
            },
            'navbar@': {},
            'topnavbar@': {},
            'footer@': {}
        },
        resolve: {
            translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                $translatePartialLoader.addPart('reset');
                return $translate.refresh();
            }]
        }
    });
}
