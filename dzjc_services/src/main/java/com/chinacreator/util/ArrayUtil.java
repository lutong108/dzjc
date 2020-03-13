package com.chinacreator.util;


/**@Title	: 数组排序工具类（学习使用）
 * @Author	: zouhailin
 * @Date	: 2018-9-12
 */
public class ArrayUtil {
	
	public static void main(String[] args) {
		int[] array = new int[]{123,14,232,231,1,34,4,43,90,48};
		print(array);
		
		array = shellSort(array);
		
		
		print(array);
	}
	
	
	/**希尔排序
	 * @param array
	 * @return
	 */
	public static int[] shellSort(int[] array) {
		int count = 0;
		if (array == null || array.length == 0) {
			return array;
		}
		int len = array.length;
		int gap = len / 2;
		while(gap>0){
			for (int i = gap; i < len; i++) {
				 int temp = array[i];
				 int preIndex = i - gap;
				 while (preIndex >= 0 && array[preIndex] > temp) {
					 count++;
					 array[preIndex + gap] = array[preIndex];
					 preIndex -= gap;
//					 print(array);
				 }
				 array[preIndex + gap] = temp;
//				 print(array);
			}
			gap /= 2;
		}
		System.out.println("shellSort:"+count);
		return array;
	}
	
	/**插入排序
	 * @param array
	 * @return
	 */
	public static int[] insertionSort(int[] array){
		int count = 0;
		if(array==null||array.length==0){
			return array;
		}
		int current;
        for (int i = 0; i < array.length - 1; i++) {
        	current = array[i + 1];
        	int preIndex = i;
            while(preIndex>=0&&current < array[preIndex]){
            	count++;
            	array[preIndex + 1] = array[preIndex];
            	preIndex--;
            	print(array);
            }
            array[preIndex + 1] = current;
        	print(array);
        }
        System.out.println("insertionSort:"+count);
        return array;
	}
	
	
	/**选择排序
	 * @param array
	 * @return
	 */
	public static int[] selectionSort(int[] array){
		int count = 0;
		if(array==null||array.length==0){
			return array;
		}
		for(int i=0;i<array.length;i++){
			int minIndex = i;
			for(int j=i+1;j<array.length;j++){
				count++;
				if(array[j]<array[minIndex]){
					minIndex = j;
				}
			}
			int temp = array[minIndex];
			array[minIndex]=array[i];
			array[i]=temp;
			print(array);
		}
		System.out.println("bubbleSort1:"+count);
		return array;
	}
	/**选择排序
	 * @param array
	 * @return
	 */
	public static int[] selectionSort1(int[] array){
		int count = 0;
		if(array==null||array.length==0){
			return array;
		}
		for(int i=0;i<array.length;i++){
			int minIndex = i;
			int maxIndex = i;
			for(int j=i+1;j<array.length;j++){
				count++;
				if(array[j]<array[minIndex]){
					minIndex = j;
				}
				if(array[j]>array[minIndex]){
					maxIndex = j;
				}
			}
			int temp = array[minIndex];
			array[minIndex]=array[i];
			array[i]=temp;
			array[maxIndex]=array[i];
			print(array);
		}
		System.out.println("bubbleSort1:"+count);
		return array;
	}
	/**冒泡法排序
	 * @param array
	 * @return
	 */
	public static int[] bubbleSort(int[] array){
		int count = 0;
		if(array==null||array.length==0){
			return array;
		}
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length-i-1;j++){
				count++;
				if(array[j]>array[j+1]){
					int temp = array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
				print(array);
			}
		}
//		System.out.println("bubbleSort:"+count);
		return array;
	}
	private static void print(int[] array){
		System.out.println();
		if(array!=null||array.length>0){
			for(int i=0;i<array.length;i++){
				System.out.print(array[i]+"\t");
			}
		}
	}
}
