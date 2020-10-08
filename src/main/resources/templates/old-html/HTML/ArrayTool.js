/*
需求：编写一个js文件，在js文件中自定义一个数组工具对象，
该工具对象要有一个找到最大值的方法，与找元素对应的索引值的方法。	
*/

//创建ArrayTool的对象
 
var tool = new ArrayTool();

function ArrayTool(){
	
	//找最大值
	this.getMax = function(arr){
		var max = arr[0];
		for(var i = 1 ; i<arr.length ;i++){
			if(arr[i]>max){
				max = arr[i];	
			}	
		}
		return max;
	}
	
	//找元素 的索引值
	this.searchEle = function(arr,target){
		for(var i = 0 ; i<arr.length  ; i++){
			if(arr[i] ==target){
				return i;	
			}	
		}	
		return -1;
	}

}




