
$(function () {
    $('#loadbtn').on('click', function () {
        $('#snippetarea').load('abc.html', function (response, status, xhr) {
            if (status === 'error') {
                console.error("Loading issue:", xhr.status, xhr.statusText);
            }
        });
    });


    $('#getdata').on('click', function () {
        $.get('https://jsonplaceholder.typicode.com/posts/1', function (data,status,xhr) {
            if (status === 'error') {
                console.error("Loading issue:", xhr.status, xhr.statusText);
            }else{

                console.log("Data :" ,data);
                $('#result').text("Title " +  data.title );
            }
        });

    
        $('#postdata').on('click', function () {
        $.post('https://jsonplaceholder.typicode.com/posts',  {

            title:"ldsjf lf" , body:"djslf" , userId : 4})
            .done(function(data) {console.log("Data posted:" , data);})
            .fail(function(){console.log("Post failed:");})
        });


          $('#ajaxbtn').on('click', function () {
        $.ajax({
            url: 'https://jsonplaceholder.typicode.com/posts/1',
            method :'Get',
            dataType :'json'
           })
            .done(function(data) {console.log("Data retreived via ajax:" , data);})
            .fail(function(){console.log("Some error:");})
            .always(function(){console.log("Task finished:");})
        });


          
    });
});
