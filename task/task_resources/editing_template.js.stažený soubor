( function($) {
   
    $(document).ready(function() {
                        
        /// displays the dialog used for editing the question text
        $('a.anketa_edit_question_text').live('click',function(event){
            
            var obj = $(this);            
            var buttonsOpts = {};
            buttonsOpts[$('div#js_cancel').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_save').text()] = function (){
                if($.trim($('#anketa_temp_question_text').val()) == ""){                            
                    $('#anketa_temp_question_text').addClass('anketa_empty_textarea');                           
                }
                else{
                    $(obj).parent().prev('span').text($('#anketa_temp_question_text').val());
                    $(obj).parent().prevAll('textarea').text($('#anketa_temp_question_text').val());
                    $(this).dialog("close");    
                }
            }
            $('#anketa_dialog_edit_question_text').dialog({
                modal: true,                
                dialogClass: 'anketa_dialog',
                resizable : false,
                position: 'center',
                width: '30em',
                open: function(){                                
                    $('#anketa_temp_question_text').val($(obj).parent().prevAll('textarea').val());  
                },                
                buttons: buttonsOpts                   
            });                           
            event.preventDefault();                
        });
        
        /// displays the dialog used for choosing type of the question
        $('a.anketa_edit_question_type').live('click',function(event){
            var obj = $(this);
            var buttonsOpts = {};
            buttonsOpts[$('div#js_cancel').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_save').text()] = function (){
                $(obj).parent().prev('span').text($('#anketa_temp_question_type option:selected').text());                                       
                $(obj).parent().prevAll('input').val($('#anketa_temp_question_type').val());
                $(this).dialog("close");                                                        
            }
            $('#anketa_dialog_edit_question_type').dialog({
                modal: true,                
                dialogClass: 'anketa_dialog',
                resizable : false,
                position: 'center',
                width: '30em',
                open: function(){                                
                    $('#anketa_temp_question_type option').each(function(index){                                  
                        if($(this).text() == $(obj).parent().prev('span').text()) $(this).attr('selected',true);
                    });
                },
                buttons: buttonsOpts
            });
                           
            event.preventDefault();
                
        });
            
        /// displays the dialog used for choosing type of the answer    
        $('a.anketa_edit_answer_type').live('click',function(event){
            var obj = $(this);
            var buttonsOpts = {};
            buttonsOpts[$('div#js_cancel').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_save').text()] = function (){
                var chosen = $('#anketa_temp_answer_type').val();
                $(obj).parent().prev('span').text($('#anketa_temp_answer_type option:selected').text());                                       
                $(obj).parent().prevAll('input').val(chosen);
                if(chosen == 'multiple' || chosen == 'just_one'){
                    $(obj).parent().parent('div').next('div.anketa_temp_answers').html($('div#js_answers_edit').html());
                    $(obj).parent().parent('div').next('div.anketa_temp_answers').find('li').remove();
                }
                else $(obj).parent().parent('div').next('div.anketa_temp_answers').html("");
                $(this).dialog("close")
            }
            $('#anketa_dialog_edit_answer_type').dialog({
                modal: true,                
                resizable : false,
                dialogClass: 'anketa_dialog',
                position: 'center',
                width: '30em',
                open: function(){                                
                    $('#anketa_temp_answer_type option').each(function(index){                                  
                        if($(this).text() == $(obj).parent().prev('span').text()) $(this).attr('selected',true);
                    });
                },
                buttons: buttonsOpts
            });
                           
            event.preventDefault();
                
        });
        /// deletes the answer
        $('.anketa_temp_delete_answer').live('click', function(event){                
            $(this).parent('li').remove();
            event.preventDefault();                
        });        
    });
        
 
 
} ) ( jQuery );
        