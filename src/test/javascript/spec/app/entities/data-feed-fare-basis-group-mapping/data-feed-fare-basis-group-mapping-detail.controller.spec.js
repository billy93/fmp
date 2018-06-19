'use strict';

describe('Controller Tests', function() {

    describe('DataFeedFareBasisGroupMapping Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDataFeedFareBasisGroupMapping;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDataFeedFareBasisGroupMapping = jasmine.createSpy('MockDataFeedFareBasisGroupMapping');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DataFeedFareBasisGroupMapping': MockDataFeedFareBasisGroupMapping
            };
            createController = function() {
                $injector.get('$controller')("DataFeedFareBasisGroupMappingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'fmpApp:dataFeedFareBasisGroupMappingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
