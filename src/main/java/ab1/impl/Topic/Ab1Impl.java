package ab1.impl.Topic;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public boolean isHeap(int i, int j, int[] data)
	{
		//Falls Arraylange 0 ist, dann ist es kein Max-Heap
		if (j == 0){
			return true;
		}
		else{
			//Falls Arraylange 1 ist, dann erfullt sowohl Max-Heap,als auch Min-Heap Bedingung
			if (j == 1){
				return true;
			}

			//Falls es mehrere Elemente gibt, wenn child node grosser als parent node ist, dann ist es kein Max-Heap
			while (i < j){

				if (data[i] > data[(i-1) / 2]){
					return false;
				}

				i++;
			}
		}

		return true;
	}
	public static void makeHeap(int[] array) {
		for (int i = (array.length - 1) / 2; i >= 0; i--) {
			heapIt(array, i, array.length - 1);
		}
	}
	public static void heapIt(int[] array, int i, int velicina) {
		//Erster node (linker)
		int lijevo = 2 * i + 1;
		//Zweiter node (rechter)
		int desno = 2 * i + 2;
		//Groester wert
		int max;
		if (lijevo <= velicina && array[lijevo] > array[i]) {
			max = lijevo;
		} else {
			max = i;
		}

		if (desno <= velicina && array[desno] > array[max]) {
			max = desno;
		}
		// Wenn max nicht der node ist,soll man in mit dem mox von links oder rechts wechseln (swapen)
		if (max != i) {
			swap(array, i, max); //recursiver aufruf
			heapIt(array, max, velicina); //recursiver aufruf
		}
	}

	public static void swap(int[] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}

	public static int[] heapSort(int[] array) {

		makeHeap(array);
		int groesseDesHeaps = array.length - 1;
		for (int i = groesseDesHeaps; i > 0; i--) {
			swap(array, 0, i);
			groesseDesHeaps = groesseDesHeaps - 1;
			heapIt(array, 0, groesseDesHeaps);
		}
		return array;
	}

	@Override
	public void toHeap(int[] data) {
		//Falls isHeap Bedingung erfullt ist, dann beenden wir Rekonstriurung von Array
		if (isHeap(1, data.length, data)) {
			return;
		}

		//Falls Bedingung nicht erfullt ist, dann passsen wir Array an, und rufen Methode wieder rekursiv
		for (int i = data.length - 1; i >= 1; i--) {

			if (data[i] > data[(i - 1) / 2]) {
				int temp = data[(i - 1) / 2];
				data[(i - 1) / 2] = data[i];
				data[i] = temp;
			}
		}

		toHeap(data);

	}


	@Override
	public void heapsort(int[] data)
	{
		heapSort(data);
	}


	public void toMaxHeap(int[] data, int length){

		//Falls schon Max-Heap ist
		if (isHeap(1,length,data)){
			return;
		}

		for (int i = length-1; i >= 1; i--){
			if (data[i] > data[(i-1)/2]){
				int temp = data[i];
				data[i] = data[(i-1)/2];
				data[(i-1)/2] = temp;
			}
		}

		//Rekursiv aufrufen solange max-heap nicht gebildet wird
		toMaxHeap(data,length);

	}

	@Override
	public ListNode insert(ListNode head, int wert)
	{
		ListNode nodeToInsert = new ListNode();
		nodeToInsert.value = wert;
		ListNode temp = new ListNode();

		//Falls head null ist oder Wert kleiner als head, dann kommt ListNode nodeToInsert an erste Stelle
		if (head == null || head.value >= nodeToInsert.value){

			temp = head;
			head = nodeToInsert;
			head.next = temp;

			return head;
		}
		else{

			//tempHead einfuhren um momentane head zu bekommen und trotzdem head Variable zu haben um spater return zu machen
			ListNode tempHead = head;

			//solange neachste Element in Liste nicht null ist und nodeToInsert grosser als dieses Element ist, schieben wir tempHead an nachste Element
			while (tempHead.next != null && nodeToInsert.value >= tempHead.next.value){
				tempHead = tempHead.next;
			}

            //falls Ende der Liste ist und nodeToInsert noch nicht eingefugt wurde, dann ist diese ListNode grosste Element und kommt am Ende von Liste (dynamischer Datenstruktur)
			if (tempHead.next == null){
				tempHead.next = nodeToInsert;


				return head;
			}


            //falls tempHead nicht am Ende gekommen ist und nodeInsert kleiner als nachste Element ist, dann muss man nodeToIsert hinfugen
			temp = tempHead.next;
			tempHead.next = nodeToInsert;
			tempHead.next.next = temp;


		}
		return head;
	}

	@Override
	public ListNode search(ListNode head, int value)
	{

		//Falls keine Elemente in Liste vorhanden sind
		if (head == null){
			return null;
		}

		//Falls Element mit gesuchte Wert gefunden ware
		while (head.next != null){
			if (head.value == value){
				return head;
			}

			head = head.next;
		}

		return null;
	}

	@Override
	public ListNode minimum(ListNode head)
	{
		//Falls es keine Elemente in List gibt
		if (head == null){
			return null;
		}

		return head;
	}

	@Override
	public void mergesort(int[] data)
	{
		int length = data.length;

		//wenn es nur 1 Element in array ist
		if (length < 2){
			return;
		}

		int middle = length / 2;

		//verteilen Array in zwei Teile
		int [] leftArray = new int[middle];
		int [] rightArray = new int[length-middle];

		int indexToLeftArray = 0;
		int indexToRightArray = 0;

		while (indexToLeftArray < leftArray.length){
			leftArray[indexToLeftArray] = data[indexToLeftArray];

			indexToLeftArray++;
		}

		while (indexToRightArray < rightArray.length){
			rightArray[indexToRightArray] = data[indexToLeftArray];

			indexToLeftArray++;
			indexToRightArray++;
		}

		mergesort(leftArray);
		mergesort(rightArray);

		 merge(data,leftArray,rightArray);
	}


	public void merge(int [] array, int [] leftArray, int [] rightArray){


		int leftArrayLength = leftArray.length;
		int rightArrayLength = rightArray.length;

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < leftArrayLength && j < rightArrayLength){

			if (leftArray[i] <= rightArray[j]){
				array[k] = leftArray[i];

				i++;
				k++;
			}
			else{
				array[k] = rightArray[j];

				j++;
				k++;
			}
		}

		while (i < leftArrayLength){
			array[k] = leftArray[i];

			i++;
			k++;
		}

		while (j < rightArrayLength){
			array[k] = rightArray[j];

			j++;
			k++;
		}

	}


}
