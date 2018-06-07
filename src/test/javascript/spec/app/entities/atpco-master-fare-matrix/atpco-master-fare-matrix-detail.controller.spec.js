'use strict';

describe('Controller Tests', function() {

    describe('AtpcoMasterFareMatrix Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAtpcoMasterFareMatrix;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAtpcoMasterFareMatrix = jasmine.createSpy('MockAtpcoMasterFareMatrix');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'AtpcoMasterFareMatrix': MockAtpcoMasterFareMatrix
            };
            createController = function() {
                $injector.get('$controller')("AtpcoMasterFareMatrixDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'fmpApp:atpcoMasterFareMatrixUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
