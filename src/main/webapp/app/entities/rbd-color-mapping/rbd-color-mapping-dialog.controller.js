(function() {
    'use strict';

    angular
        .module('fmpApp')
        .controller('RbdColorMappingDialogController', RbdColorMappingDialogController);

    RbdColorMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RbdColorMapping'];

    function RbdColorMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RbdColorMapping) {
        var vm = this;

        vm.colorVal = '';
        vm.rbdColorMapping = entity;
        vm.clear = clear;
        vm.save = save;
        vm.colorCodes = [
			{key : 	"IndianRed",	value :	"205,92,92"	},
			{key : 	"LightCoral",	value :	"240,128,128"	},
			{key : 	"Salmon",	value :	"250,128,114"	},
			{key : 	"DarkSalmon",	value :	"233,150,122"	},
			{key : 	"LightSalmon",	value :	"255,160,122"	},
			{key : 	"Crimson",	value :	"220,20,60"	},
			{key : 	"Red",	value :	"255,0,0"	},
			{key : 	"FireBrick",	value :	"178,34,34"	},
			{key : 	"DarkRed",	value :	"139,0,0"	},
			{key : 	"Pink",	value :	"255,192,203"	},
			{key : 	"LightPink",	value :	"255,182,193"	},
			{key : 	"HotPink",	value :	"255,105,180"	},
			{key : 	"DeepPink",	value :	"255,20,147"	},
			{key : 	"MediumVioletRed",	value :	"199,21,133"	},
			{key : 	"PaleVioletRed",	value :	"219,112,147"	},
			{key : 	"Coral",	value :	"255,127,80"	},
			{key : 	"Tomato",	value :	"255,99,71"	},
			{key : 	"OrangeRed",	value :	"255,69,0"	},
			{key : 	"DarkOrange",	value :	"255,140,0"	},
			{key : 	"Orange",	value :	"255,165,0"	},
			{key : 	"Gold",	value :	"255,215,0"	},
			{key : 	"Yellow",	value :	"255,255,0"	},
			{key : 	"LightYellow",	value :	"255,255,224"	},
			{key : 	"LemonChiffon",	value :	"255,250,205"	},
			{key : 	"LightGoldenrodYellow",	value :	"250,250,210"	},
			{key : 	"PapayaWhip",	value :	"255,239,213"	},
			{key : 	"Moccasin",	value :	"255,228,181"	},
			{key : 	"PeachPuff",	value :	"255,218,185"	},
			{key : 	"PaleGoldenrod",	value :	"238,232,170"	},
			{key : 	"Khaki",	value :	"240,230,140"	},
			{key : 	"DarkKhaki",	value :	"189,183,107"	},
			{key : 	"Lavender",	value :	"230,230,250"	},
			{key : 	"Thistle",	value :	"216,191,216"	},
			{key : 	"Plum",	value :	"221,160,221"	},
			{key : 	"Violet",	value :	"238,130,238"	},
			{key : 	"Orchid",	value :	"218,112,214"	},
			{key : 	"Fuchsia",	value :	"255,0,255"	},
			{key : 	"Magenta",	value :	"255,0,255"	},
			{key : 	"MediumOrchid",	value :	"186,85,211"	},
			{key : 	"MediumPurple",	value :	"147,112,219"	},
			{key : 	"BlueViolet",	value :	"138,43,226"	},
			{key : 	"DarkViolet",	value :	"148,0,211"	},
			{key : 	"DarkOrchid",	value :	"153,50,204"	},
			{key : 	"DarkMagenta",	value :	"139,0,139"	},
			{key : 	"Purple",	value :	"128,0,128"	},
			{key : 	"RebeccaPurple",	value :	"102,51,153"	},
			{key : 	"Indigo",	value :	"75,0,130"	},
			{key : 	"MediumSlateBlue",	value :	"123,104,238"	},
			{key : 	"SlateBlue",	value :	"106,90,205"	},
			{key : 	"DarkSlateBlue",	value :	"72,61,139"	},
			{key : 	"GreenYellow",	value :	"173,255,47"	},
			{key : 	"Chartreuse",	value :	"127,255,0"	},
			{key : 	"LawnGreen",	value :	"124,252,0"	},
			{key : 	"Lime",	value :	"0,255,0"	},
			{key : 	"LimeGreen",	value :	"50,205,50"	},
			{key : 	"PaleGreen",	value :	"152,251,152"	},
			{key : 	"LightGreen",	value :	"144,238,144"	},
			{key : 	"MediumSpringGreen",	value :	"0,250,154"	},
			{key : 	"SpringGreen",	value :	"0,255,127"	},
			{key : 	"MediumSeaGreen",	value :	"60,179,113"	},
			{key : 	"SeaGreen",	value :	"46,139,87"	},
			{key : 	"ForestGreen",	value :	"34,139,34"	},
			{key : 	"Green",	value :	"0,128,0"	},
			{key : 	"DarkGreen",	value :	"0,100,0"	},
			{key : 	"YellowGreen",	value :	"154,205,50"	},
			{key : 	"OliveDrab",	value :	"107,142,35"	},
			{key : 	"Olive",	value :	"128,128,0"	},
			{key : 	"DarkOliveGreen",	value :	"85,107,47"	},
			{key : 	"MediumAquamarine",	value :	"102,205,170"	},
			{key : 	"DarkSeaGreen",	value :	"143,188,143"	},
			{key : 	"LightSeaGreen",	value :	"32,178,170"	},
			{key : 	"DarkCyan",	value :	"0,139,139"	},
			{key : 	"Teal",	value :	"0,128,128"	},
			{key : 	"Aqua",	value :	"0,255,255"	},
			{key : 	"Cyan",	value :	"0,255,255"	},
			{key : 	"LightCyan",	value :	"224,255,255"	},
			{key : 	"PaleTurquoise",	value :	"175,238,238"	},
			{key : 	"Aquamarine",	value :	"127,255,212"	},
			{key : 	"Turquoise",	value :	"64,224,208"	},
			{key : 	"MediumTurquoise",	value :	"72,209,204"	},
			{key : 	"DarkTurquoise",	value :	"0,206,209"	},
			{key : 	"CadetBlue",	value :	"95,158,160"	},
			{key : 	"SteelBlue",	value :	"70,130,180"	},
			{key : 	"LightSteelBlue",	value :	"176,196,222"	},
			{key : 	"PowderBlue",	value :	"176,224,230"	},
			{key : 	"LightBlue",	value :	"173,216,230"	},
			{key : 	"SkyBlue",	value :	"135,206,235"	},
			{key : 	"LightSkyBlue",	value :	"135,206,250"	},
			{key : 	"DeepSkyBlue",	value :	"0,191,255"	},
			{key : 	"DodgerBlue",	value :	"30,144,255"	},
			{key : 	"CornflowerBlue",	value :	"100,149,237"	},
			{key : 	"RoyalBlue",	value :	"65,105,225"	},
			{key : 	"Blue",	value :	"0,0,255"	},
			{key : 	"MediumBlue",	value :	"0,0,205"	},
			{key : 	"DarkBlue",	value :	"0,0,139"	},
			{key : 	"Navy",	value :	"0,0,128"	},
			{key : 	"MidnightBlue",	value :	"25,25,112"	},
			{key : 	"Cornsilk",	value :	"255,248,220"	},
			{key : 	"BlanchedAlmond",	value :	"255,235,205"	},
			{key : 	"Bisque",	value :	"255,228,196"	},
			{key : 	"NavajoWhite",	value :	"255,222,173"	},
			{key : 	"Wheat",	value :	"245,222,179"	},
			{key : 	"BurlyWood",	value :	"222,184,135"	},
			{key : 	"Tan",	value :	"210,180,140"	},
			{key : 	"RosyBrown",	value :	"188,143,143"	},
			{key : 	"SandyBrown",	value :	"244,164,96"	},
			{key : 	"Goldenrod",	value :	"218,165,32"	},
			{key : 	"DarkGoldenrod",	value :	"184,134,11"	},
			{key : 	"Peru",	value :	"205,133,63"	},
			{key : 	"Chocolate",	value :	"210,105,30"	},
			{key : 	"SaddleBrown",	value :	"139,69,19"	},
			{key : 	"Sienna",	value :	"160,82,45"	},
			{key : 	"Brown",	value :	"165,42,42"	},
			{key : 	"Maroon",	value :	"128,0,0"	},
//			{key : 	"White",	value :	"255,255,255"	},
//			{key : 	"Snow",	value :	"255,250,250"	},
//			{key : 	"Honeydew",	value :	"240,255,240"	},
//			{key : 	"MintCream",	value :	"245,255,250"	},
//			{key : 	"Azure",	value :	"240,255,255"	},
//			{key : 	"AliceBlue",	value :	"240,248,255"	},
//			{key : 	"GhostWhite",	value :	"248,248,255"	},
//			{key : 	"WhiteSmoke",	value :	"245,245,245"	},
//			{key : 	"Seashell",	value :	"255,245,238"	},
//			{key : 	"Beige",	value :	"245,245,220"	},
//			{key : 	"OldLace",	value :	"253,245,230"	},
//			{key : 	"FloralWhite",	value :	"255,250,240"	},
//			{key : 	"Ivory",	value :	"255,255,240"	},
//			{key : 	"AntiqueWhite",	value :	"250,235,215"	},
//			{key : 	"Linen",	value :	"250,240,230"	},
//			{key : 	"LavenderBlush",	value :	"255,240,245"	},
//			{key : 	"MistyRose",	value :	"255,228,225"	},
//			{key : 	"Gainsboro",	value :	"220,220,220"	},
//			{key : 	"LightGray",	value :	"211,211,211"	},
//			{key : 	"LightGrey",	value :	"211,211,211"	},
//			{key : 	"Silver",	value :	"192,192,192"	},
//			{key : 	"DarkGray",	value :	"169,169,169"	},
//			{key : 	"DarkGrey",	value :	"169,169,169"	},
//			{key : 	"Gray",	value :	"128,128,128"	},
//			{key : 	"Grey",	value :	"128,128,128"	},
//			{key : 	"DimGray",	value :	"105,105,105"	},
//			{key : 	"DimGrey",	value :	"105,105,105"	},
//			{key : 	"LightSlateGray",	value :	"119,136,153"	},
//			{key : 	"LightSlateGrey",	value :	"119,136,153"	},
//			{key : 	"SlateGray",	value :	"112,128,144"	},
//			{key : 	"SlateGrey",	value :	"112,128,144"	},
//			{key : 	"DarkSlateGray",	value :	"47,79,79"	},
//			{key : 	"DarkSlateGrey",	value :	"47,79,79"	},
//			{key : 	"Black",	value :	"0,0,0"	}
		];
        
        vm.selectedColor = null;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            vm.rbdColorMapping.colorVal = vm.colorVal;
            if (vm.rbdColorMapping.id !== null) {
                RbdColorMapping.update(vm.rbdColorMapping, onSaveSuccess, onSaveError);
            } else {
                RbdColorMapping.save(vm.rbdColorMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fmpApp:rbdColorMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
