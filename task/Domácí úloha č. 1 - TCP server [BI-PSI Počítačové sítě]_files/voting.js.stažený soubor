( function($) {
   
    $(document).ready(function() {
        var sent = false;
    
        /// validates the voting
        function checkVoting(event){
       
            $('#anketa_error_voting').remove();
            var voting_error = false;
            $('textarea.anketa_answer_text_own_required').each(function(index){
            
                if($.trim($(this).val()) == ""){                
                    voting_error = true;
                    $(this).addClass('anketa_empty_textarea');
                    $(this).parent('div').prev('.anketa_poll_question').addClass('anketa_empty_question');                            
                }
                else {                
                    $(this).removeClass('anketa_empty_textarea');            
                    $(this).parent('div').prev('.anketa_poll_question').removeClass('anketa_empty_question');
                }
            
            });
        
            $('.anketa_poll_question_input').each(function(index){            
            
                if($(this).next('div.anketa_poll_answers').find('input:checked').length == 0){
                    $(this).addClass('anketa_empty_question');
                    if($('#anketa_error_voting').size() == 0) voting_error = true;
                } 
                else $(this).removeClass('anketa_empty_question');
            });           
                
            if(voting_error){ 
                $('#anketa_poll_vote').parent('div').before('<p id="anketa_error_voting">'+ $('div#js_error_voting').text() +'</p>');
                event.preventDefault();
            }                         
        }
        $('.anketa_answer_text_own_required').blur(function(event){        
            if(sent) checkVoting(event); 
        });
    
    
        $('#anketa_poll_form input').change(function(event){            
            if(sent) checkVoting(event);          
        });
    
    
        $('#form_poll_vote').submit(function(event){                   
            checkVoting(event);
            sent = true;
                 
        }); 
     
        /// if the form was not filled in correctly, dialog will be automatically displayed 
        var autoDisplay = false;
        if($('p#anketa_error_voting').text() != "") autoDisplay = true;
     
        var jDialog = $( '#anketa_dialog_form').dialog({                
            modal: true,                 
            draggable: false,
            resizable: false,
            width: "52em",                
            dialogClass: 'anketa_voting anketa_dialog',
            autoOpen: autoDisplay,
            open: function(){
                $('.ui-widget-overlay').bind('click',function(){                
                    $('#anketa_dialog_form').dialog('close');
                })
                $(this).css({
                    'max-height': $(window).height()*0.8,                         
                    'overflow-y': 'auto'
                });                     
            }  
        });
    
        $('#anketa_display_poll').live('click',function(event){            
            jDialog.dialog('open');            
            event.preventDefault();  
        });

        // there is a link to vote/display results on the page
        if($('div#anketa_link_container').size() > 0){            
            var link = $('div#anketa_link').html();
            var headline = $('div#anketa_link_container h2 a').html();
            $('div#anketa_link_container').hide();
            if($('div.plugin_translation').size() > 0){
                $('div.page_with_sidebar div.plugin_translation').after('<div id="anketa_moved_link_container"><div>'+ headline  +'</div><div>'+link+'</div></div>');
            }
            else $('div.page_with_sidebar').prepend('<div id="anketa_moved_link_container"><div>'+ headline  +'</div><div>'+link+'</div></div>');
            
        }
    
        function getParameterByName(name)
        {
            name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
            var regexS = "[\\?&]" + name + "=([^&#]*)";
            var regex = new RegExp(regexS);
            var results = regex.exec(window.location.search);
            if(results == null)
                return "";
            else
                return decodeURIComponent(results[1].replace(/\+/g, " "));
        }
    
        if(getParameterByName('anketa') == 'show'){
            jDialog.dialog('open'); 
        }
    
    });  
    
} ) ( jQuery );