$(function(){

     $('.title').css('border','2px dashed');

    $('input[type="text"]').val("Wipro");

    $('p').css('background' , "#F54927")
   

    $('#show').on('click', function(){$('#box').fadeIn('slow');});
    $('#hide').on('click', function(){$('#box').fadeOut('400');});
    $('#fadeto').on('click', function(){$('#box').fadeTo('slow',0.5);});
   

     $('#slidder').on('click', function(){$('#panel').slideToggle('slow');});
});

 
 