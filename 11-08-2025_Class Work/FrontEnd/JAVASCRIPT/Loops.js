// for(let i=1;i<=5 ; i++)
// {
//    alert("The index no is :" + i)
//    document.write("The index no is :" + i);
//    console.log("The index no is :" + i)

// }

// for(let j=5;j>=1 ; j--)
// {

//    console.log("The index no is :" + j)

// }

// let k=1;

// while(k<=5)
//     console.log("The index no is :" + k)
// k++;

// let l=1


// // the loop will get executed at least once even if the condition is failed
// do{

// console.log("The current index no. is" + l)
// l++;
// }
// while(l<=5)

// let arrColors =["Red" , "Green","Yellow" , "Blue"];

// for(let colors in arrColors)
// {

//     document.writeln("<li>Color name is :" + arrColors[colors]  + "</li>")
// }

// let objEmployee = {'empName' : 'Niti', 'empAddress' : " Delhi" , "empAge" : "45"};
// document.writeln(" Employees detail are : ");
// for (emp in objEmployee)
// {
     
//     document.writeln( emp + " is " + objEmployee[emp]);
// }

// inbuilt objects -- loop through all th properties in the inbuilt object
// for(props in window)
// {

//     document.writeln(props);

// }

// let name = " Niti Dwivedi";
// for(let letters in name)
// {
// document.writeln("Letter :" + name[letters]);

// }

// let arrDays =["Monday" , " Tuesday" , "Wednesday" , " Thursday"]

// let i=1;
// while(i<=5)
// {
// if (i==5)
// {
//     break;
// }
// console.log(i);
// i++;
// }

// i=1;
// while(i<=20)
// {

//     if(i%2== 0)
//     {
//         console.log("Even number :" + i);
//         i++;
//         continue; // It will skip rest of the loop body you have defined

//         i+50;
//         console.log("shfkhfk");
//     }
//     console.log('Odd Number :' + i);
//     i++
// }

// break statement is used to exit a loop early , to jump out of a loop which will stop the execution 
// continue statement tells the interpreter to immediately start the next iteration of the loop and skip the remaining code block 

// let userName = "admi"
// if(userName == "admin")
// {
//     window.alert("Welcome :" + userName);
// }
// else
// {
//     window.alert("Wrong User Name entered :");
// }

// let age = 15
// if(age >= 60)
// {
//     window.alert("Welcome to Senior Citizen Family :" + age);
// }
// else if(age <18)
// {
//     window.alert("Welcome to Minor Family :" + age);
// }
// else
// {
//     console.log("Middle Age :" + age)

// }


var choice = prompt("Enter your choice :");
switch(choice)
{

case 'A':
    document.writeln("Great Job (Add a Student):");
    break;

case 'B':
    document.writeln("Great Job (Delete a Student):");
    break;

case 'C':
    document.writeln("Great Job (Display a Student):");
    break;
default :
    document.writeln("Wrong Choice : try again!" );
    break;
}
