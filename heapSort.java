import java.util.Scanner;

public class heapSort{
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int[] arr=new int[10000];
		for(int i=0;i<10000;i++)
			arr[i]=scan.nextInt();
		Heap.HeapSort(arr);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]+" ");
		}
		System.out.println();

	}

	static class Heap{
		private int heapSize;
		private int[] arr;
		private int heapCapacity;

		Heap(int heapCapacity){
			this.heapCapacity=heapCapacity;
			heapSize=0;
			arr=new int[heapCapacity];
		}

		Heap(int heapCapacity,int[] data){
			this.heapCapacity=heapCapacity;
			heapSize=data.length;
			int min=data.length;
			if(heapCapacity<data.length)
				min=heapCapacity;
			arr=new int[heapCapacity];
			for (int x=0;x<min;x++)
				arr[x]=data[x];
			initailize();
		}

		void print(){
			for(int i=0;i<heapSize;i++)
				System.out.print(arr[i]+" ");
			System.out.println();
		}

		int min(){
			return arr[0];
		}

		int ExtractMin(){
			int temp=arr[0];
			arr[0]=arr[heapSize-1];
			heapSize--;
			heapify(0);
			return temp;
		}

		private void heapify(int i){
			int left=2*i+1;
			int right=left+1;
			int key=arr[i];
			int min;
			while(left<heapSize){
				if(arr[left]<key)
					min=left;
				else
					min=i;
				if(right<heapSize && arr[right]<arr[min])
					min=right;
				if(min!=i){
					arr[i]=arr[min];
				}
				else
					break;
				i=min;
				left=2*i+1;
				right=left++;
			}
			arr[i]=key;
		}

		void insert(int elem){
			if(heapSize==heapCapacity){
				heapCapacity*=2;
				int[] temp=new int[heapCapacity];
				for(int i=0;i<heapCapacity/2;i++)
					temp[i]=arr[i];
				arr=temp;
			}
			arr[heapSize]=elem;
			upSwapper(heapSize++);
		}

		private void upSwapper(int i){
			int key=arr[i];
			int parent=(i+1)/2-1;
			while(parent>=0 && arr[parent]>key){
				arr[i]=arr[parent];
				i=parent;
				parent=(i+1)/2-1;
			}
			arr[i]=key;
		}

		private void initailize(){
			int i=(heapSize)/2-1;
			for(;i>=0;i--){
				heapify(i);
			}
		}

		static void HeapSort(int[] arr){
			int i=arr.length-1;
			Heap H=new Heap(arr.length,arr);
			for (;i>=0;i--){
				arr[i]=H.ExtractMin();
			}
		}
	}
}
