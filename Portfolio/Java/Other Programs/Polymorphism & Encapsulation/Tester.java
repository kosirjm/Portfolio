package Tester;

import Shape.*;
/**
 * The Point class for Lab 4 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 21 2012
 */

public class Tester {

	public static void main(String[] args) {
		// 1. Create an array of 4 shape object references.
		Shape[] shapeArray = new Shape[4];
		
		/* 2. Create a Point object named 'center' with value (1, 2.5) and 
		 * use it initialize an array of Point references of length 1.
		 * Use the array to create a Circle object with radius 4.5 and
		 * assign it to the 0 element of the Shape reference array.
		 */
		Point center = new Point(1.0, 2.5);
		Point[] circlePoint = new Point[1];
		circlePoint[0] = center;
		shapeArray[0] = new Circle(circlePoint, 4.5);
		
		
		/* 3. Create an array of three Point references with points (0,0), 
		 * (2.4, -1), and (-2.9, -4). Use this array to instantiate a Triangle 
		 * object and assign it to the 1 element of the Shape reference array.
		 */
		Point[] trianglePoints = new Point[3];
		trianglePoints[0] = new Point(0, 0);
		trianglePoints[1] = new Point(2.4, -1);
		trianglePoints[2] = new Point(-2.9, -4);
		shapeArray[1] = new Triangle(trianglePoints);
		
		/* 4. Create an array of four Point references using Points
		 * (1,2), (4,2), (4,6), and (1,6). Assign it to the 2 element of the 
		 * Shape reference array.
		 */
		Point[] rectanglePoints = new Point[4];
		rectanglePoints[0] = new Point(1, 2);
		rectanglePoints[1] = new Point(4, 2);
		rectanglePoints[2] = new Point(4, 6);
		rectanglePoints[3] = new Point(1, 6);
		shapeArray[2] = new Rectangle(rectanglePoints);
		
		/*
		 * 5. Create an array of three Point objects (-1,0.5), (2,2) and (0,4).
		 * Use this array to instantiate a Triangle object 
		 * and assign the reference to the 3 element of the Shape reference array.
		 */
		Point[] trianglePoints2 = new Point[3];
		trianglePoints2[0] = new Point(-1, 0.5);
		trianglePoints2[1] = new Point(2, 2);
		trianglePoints2[2] = new Point(0, 4);
		shapeArray[3] = new Triangle(trianglePoints);
		
		/* 6. Compute and display the summed areas of all shapes in the array by calling getArea
		 * on the array elements.
		 */
		for(int i = 0; i < shapeArray.length; i++)
		{
			System.out.println(shapeArray[i].getArea());
		}
		
		/* 7. Verify that all Shape subclasses use proper encapsulation, meaning their component
		 * Point attributes can't be modified through changes to the Point objects used
		 * to initialize the individual Shapes.
		 * 
		 */
		trianglePoints2[0] = new Point(400, 400);
		rectanglePoints[0] = new Point(400, 400);
		trianglePoints[0] = new Point(400, 400);
		circlePoint[0] = new Point(400, 400);
		System.out.println(" After Point Change");
		for(int i = 0; i < shapeArray.length; i++)
		{
			System.out.println(shapeArray[i].getArea());
		}
		
		
}
}
