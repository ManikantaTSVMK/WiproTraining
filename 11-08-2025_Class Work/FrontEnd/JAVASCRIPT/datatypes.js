// use typeof to find data types of variables

alert(typeof "Hello");
alert(typeof "12");


console.log(typeof(3434));
console.log(typeof(34.34));

// boolean value
console.log(3 == 4);

//var a ;
//console.log(a);
console.log(typeof undefined);

var x;
console.log("Type of x is :" + typeof x); // undefined

console.log(typeof null); // object
var x= null
console.log("Type of x is :" + typeof x); // object

alert(typeof document); // object
alert(typeof window); // object
alert(typeof {name : "Niti", age:34}); // object  

// Arrays
var x =[];
console.log("type of x :" + typeof x); // object
console.log(typeof ['dsf','dsfdsf','dsfsf']); // object
console.log(typeof ['dsf',45,'dsfsf']); // object

console.log(typeof function(){});  //function
console.log(typeof alert); // function
console.log(typeof window.alert); //function

alert(typeof Symbol("dob")); // symbol


// let , var or const
//1.  var is a function scoped or globally scoped  type of variable which is declared inside a function and
// only be accessible within that function if declared outside any function the it's
// globally accessible
var value = "skfhskf";
var age =43;
const pi = 3.14;
let age1 = 34;

var isFemale = true;
var isSenior = false;

var num1=45;
var num2=34;
var isEqual = (num1==num2);
console.log(isEqual);


var num123 = 56;
function sayHello(){

    var num12 = 56;


}

// Let -- are only block scoped -- if {}, for()

if(true)
{
let y=45;
const result = 67;
console.log(y);
console.log(result)

}
//console.log(result) // will give an error as not defined -- reference error

// 2. hoisting -with var- difference
console.log(ab); //  it is undefined and you can access it before its declaration
var ab=45;

//hoisting with let 
//console.log(abc); //  immediately it will give an error : cannot access abc before initialization
let abc=45;

// 3. Mutability (Reassignment and Redecalaration)

// Var : can be reassigned and redecalared within the same scope
var name = " Niti";
var name = "Jiya"; // Redecalaring
name = "Shyam"; // Reassigned

//let : can be reassigned but cannot be redeclared within the same scope

let agge=45;

agge =56; // reassigning is possible

//let agge =67; // syntax error 


// const -- it will be a fixed type of value which cannot be reassigned and redeclared

const pii =3.14;
//pii=56.78 //Typeerror 
//const pii = 3.14444; // error

const person = {name : "Niti"};
person.name = "Jiya"; // It will allow because modifying the property is allowed

 //person = {name : "Shyam"}; // type error : Assignment

 //const person ={age :34};