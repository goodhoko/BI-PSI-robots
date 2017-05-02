( function($) {
   
    $(document).ready(function() {
    
        /// flag if some text of the question is not filled
        var emptyQuestion = false;
        /// flag there is less than two answers by the question
        var fewAnswers = false;   
        /// flag if some answer is not filled
        var emptyAnswer = false;
        /**
         * flag there is less than two answers by the question
         * in the Edit section
         */
        var fewAnswersEdit = false;
        /// number of last created question
        var current;
        /// flag if there was a try to submit the form
        var sent = false;     
        /// flag if there is an incorrect name of the template/poll
        var errorName = false;
        
        /**************************************************************************************************/
        
        current = $('.anketa_question').size() - 1;        
        
        /// displays the dialog which contains the list of the compulsory questions
        $('#anketa_display_questions').click(function(event){
            $( '#anketa_dialog_display_questions').dialog({               
                resizable : false,
                draggable: false,                
                modal: true,                
                width: "50em",
                dialogClass: 'anketa_dialog',                
                open: function(event,ui){     
                    $('.ui-widget-overlay').bind('click',function(){               
                        $('#anketa_dialog_display_questions').dialog('close');
                    });                      
                    $(event.target).parent().css({
                        'max-height': $(window).height()*0.8,
                        'overflow-y': 'auto',                           
                        'position' : 'fixed',
                        'top' : '5%'
                    });                     
                }  
            });
            event.preventDefault();  
        }); 
        
        /// adds question
        $('#anketa_create_question' ).click(function(event){            
            current++;
            $('div#js_question').find('span.anketa_order').text(current);
            $(this).parent('p').before($('div#js_question').html()); 
            $(this).parent('p').prev('div').find('textarea.anketa_question_text').focus();
            $('#anketa_error_no_question').remove();
            if($('#anketa_poll_error').text() == "") $('#anketa_poll_error').hide();
            $('#anketa_create_question').removeClass('anketa_empty_textarea_green'); 
    
            event.preventDefault();  
        });
    
       
        /// minimalization/maximalization of the content of the question
        $('a.anketa_toggle_question').live('click', function(event){        
            $(this).parent('span.anketa_options').next('div.anketa_question_body').slideToggle('medium');
            $(this).text(hideShow($(this).text())); 
            
            event.preventDefault();  
        });
    
        /// deletes the whole question
        $('a.anketa_delete_question').live('click', function(event){
            current--;
            var id = $(this).parent('span').prevAll('span.anketa_question_label').children('span.anketa_order').text();
    
            $('.anketa_order').text(function(index,text){                                     
                var por = index - 1;                
                if(text > id){
                    $(this).parents('div.anketa_question').find('textarea.anketa_create_answer_text').attr('name','answers_' + por +'[]');                              
                    return text-1;
                }
                                  
                return text;     
            });           
            
            $(this).parents('.anketa_question').remove();            
            if(sent) checkAll($('input[name=form_type]').val());
            event.preventDefault();     
        });
        
        /// reacts to the change of add compulsory question form
        $('input.anketa_add_compulsory').change(function(event){            
            if(sent) checkAll($('input[name=form_type]').val());            
        });
        
        /// reacts to selection of the template, loads the recommended poll id using ajax
        $('select#NewPollForm-template_id').change(function(event){            
            $.ajax({
                type: "POST",         
                url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                async: false,
                cache: false,
                data: {                        
                    "action": "suggest_poll_id",
                    "template_id": $(this).val()
                },            
                success: function(data){                                                                     
                    $('input#NewPollForm-poll_id').val(data);
                    checkName('poll_id',true);
                }   
            });
           
        });
   
        /// reacts to change of the answer type
        $('select[name=answer_type[]]').live('change', function(event){
              
            var val = $(this).val();        
            if(val == 'just_one' || val == 'multiple'){
                var por = $(this).parents('div.anketa_question').find('.anketa_order').text() - 1;
                $('div#js_answer').find('textarea').attr('name','answers_'+ por +'[]');
                $(this).parents('div.anketa_choices').next('.anketa_answers').html($('div#js_answer').html());   
                $(this).parent('div.anketa_choices').next('div.anketa_answers').find('textarea.anketa_create_answer_text').focus();    
            }
            else $(this).parent('div.anketa_choices').next('.anketa_answers').html("");
            if(sent) checkAll($('input[name=form_type]').val());      
    
        });  
    
        /// adds new answer
        $('a.anketa_create_answer').live('click', function(event){
            var por = $(this).parents('div.anketa_question').find('.anketa_order').text() - 1;
            $(this).parent('p').before(
                '<div class="anketa_answer">' + 
                '   <textarea cols="50" class="anketa_create_answer_text"  rows="3" name="answers_' + por +'[]"></textarea>' +
                '   <a href="#" class="anketa_delete_answer"><img class="anketa_delete_answer" src="' + DOKU_BASE + 
                'lib/plugins/anketa/images/delete_answer.png" alt="odstranit odpověď" /></a>' +
                '</div>');
            $(this).parent('p').prev('p.anketa_answer').find('textarea.anketa_create_answer_text').focus();
            if(sent) checkAll($('input[name=form_type]').val());
            event.preventDefault();           
        });
        
        $('.anketa_question_text').live('blur',function(event){
            if(sent) checkAll($('input[name=form_type]').val());
        });
    
        $('.anketa_create_answer_text').live('blur',function(event){
            if(sent) checkAll($('input[name=form_type]').val());
        });              
        
        $('.anketa_delete_answer').live('click', function(event){                                   
            $(this).parents('.anketa_answer').remove();        
            if(sent) checkAll($('input[name=form_type]').val());
            event.preventDefault();  
        });       
        
        $('.anketa_edit_answer_text').live('click', function(event){
            showDialogEditAnswer($(this),false);                 
            event.preventDefault();
        });        
   
        $('.anketa_temp_delete_answer').live('click', function(event){                
            $(this).parents('li').remove();
            if(sent)checkAll($('input[name=form_type]').val());
            event.preventDefault();                
        });
           
        $('.anketa_where_insert').click(function(event){    
            if(sent) checkAll($('input[name=form_type]').val());
        });
   
                
        $('.anketa_temp_add_answer').live('click', function(event){
            var por = $(this).parents('div.anketa_question').find('.anketa_order').text() - 1;
            $('div#js_edit_add_answer').find('textarea').attr('name', "answers_"+por+"[]");
            $(this).prev('ul').append($('div#js_edit_add_answer ul').html());
            showDialogEditAnswer($(this).prev('ul').find('.anketa_edit_answer_text:last'), true);                             
            event.preventDefault();
        });
        
        
        $('input.anketa_where_insert').change(function(event){            
            var obj = $(this);
            if($(this).attr('checked') == true ) alreadyContainsPoll($(obj),$.trim($(this).val()));           
        });
        
        $('input[name=template_id]').blur(function(event){            
            checkName('template_id',true);   
                       
        });
        
        $('input[name=poll_id]').blur(function(event){
            checkName('poll_id',true);            
        });
        
        /**
         * show dialog that displays form to edit text of the answer
         */
        function showDialogEditAnswer(obj, empty){
                
            var buttonsOpts = {};
            buttonsOpts[$('div#js_cancel').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_save').text()] = function (){                        
                if($.trim($('#anketa_temp_answer_text').val()) == ""){                            
                    $('#anketa_temp_answer_text').addClass('anketa_empty_textarea');                           
                            
                }
                else{                            
                    $(obj).parent().prev('span').text($('#anketa_temp_answer_text').val());
                    $(obj).parent().prevAll('textarea').text($('#anketa_temp_answer_text').val());
                    if(sent)checkAll($('input[name=form_type]').val());
                    $(this).dialog("close");                     
                }   
            }            
             
            $('#anketa_dialog_edit_answer_text').dialog({
                modal: true,                
                resizable : false,
                position: 'center',
                dialogClass: 'anketa_dialog',
                width: '30em',
                open: function(){                        
                    if(!empty)$('#anketa_temp_answer_text').val($(obj).parent().prevAll('textarea').val());                                
                    else $('#anketa_temp_answer_text').val("");
                },
                buttons: buttonsOpts
            });                
        }
        
        /**************************************************************************/
   
        $('form#NewPollForm').submit(function(event){                
            if(!checkAll("newPoll")) event.preventDefault();                                 
            sent = true;    
        });
        $('form#NewTemplateForm').submit(function(event){                
            if(!checkAll("newTemplate")) event.preventDefault();                               
            sent = true;    
        });
                        
        $('form#EditTemplateForm').submit(function(event){                        
            if(!checkAll("editTemplate")) event.preventDefault();                      
            sent = true;    
        });
        
        $('form#ChooseTemplateToDeleteForm').submit(function(event){
            event.preventDefault();
            var buttonsOpts = {};
            buttonsOpts[$('div#js_no').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_yes').text()] = function (){    
                $(this).dialog("close");                        
                form.submit();
            }
            var form = this;
            $("#anketa_dialog_confirm_delete").dialog({
                modal: true,
                resizable : false,
                position: 'center',
                dialogClass: 'anketa_confirm anketa_dialog',
                buttons: buttonsOpts,
                closeOnEscape: false                            
            });    
        });        
        
        $('form#ChoosePollToDeleteForm').submit(function(event){
            event.preventDefault();
            var buttonsOpts = {};
            buttonsOpts[$('div#js_no').text()] = function (){
                $(this).dialog("close");
            }
            buttonsOpts[$('div#js_yes').text()] = function (){    
                $(this).dialog("close");                        
                form.submit();
            }
            var form = this;
            $("#anketa_dialog_confirm_delete").dialog({
                modal: true,
                resizable : false,
                position: 'center',
                dialogClass: 'anketa_confirm anketa_dialog',
                buttons: buttonsOpts,            
                closeOnEscape: false                            
            });
        });
    
   
        /*********************************************** file tree ****************************************/	
        
        $('.anketa_pft-directory a').click( function(event) {
            $(this).parent().find("ul:first").slideToggle('medium');
            if( $(this).parent().attr('className') == "anketa_pft-directory" ) return false;
        });
        
        $('input.anketa_where_insert:checked').each(function(){

            $(this).parents('ul').show();
        });
    
        /****************************************** validating functions ***********************************/
    
        /**
         * checks the whole form (Create new template/ Edit template)
         */
        function checkAll(type){
    
            emptyQuestion = false;            
            fewAnswers = false;
            fewAnswersEdit = false;
            emptyAnswer = false;
    
            $('#anketa_poll_error').text("");            
            if(type == "newPoll"){
                checkName('poll_id', false);    
                if(!checkIfChosenWhere()) $('#anketa_poll_error').append('<div id="anketa_error_no_where" class="anketa_error_line">'+ $('div#js_not_chosen_where').text() + '</div>');                          
            }
    
            else if(type == "newTemplate" || type == "editTemplate"){
                if(type == "newTemplate") checkName('template_id', false);    
                $('.anketa_question').each(function(index){                             
                    if(index != $('.anketa_question').size()-1) checkQuestion($(this));        
                });
                if(($('input.anketa_add_compulsory:checked').val() != "yes"  || type == "editTemplate") && current == 0){               
                    $('#anketa_poll_error').append('<div id="anketa_error_no_question" class="anketa_error_line">'+ $('div#js_no_question').text() + '</div>');
                    $('#anketa_create_question').addClass('anketa_empty_textarea_green');                 
                }  
                else $('#anketa_create_question').removeClass('anketa_empty_textarea_green');                 
                
            }             
            
            if(emptyQuestion) $('#anketa_poll_error').append('<div id="anketa_error_empty_question" class="anketa_error_line">'+ $('div#js_question_not_filled').text() + '</div>');                     
            if(fewAnswers || fewAnswersEdit) $('#anketa_poll_error').append('<div id="anketa_error_few_answers" class="anketa_error_line">'+ $('div#js_few_answers').text() + '</div>');         
            if(emptyAnswer)  $('#anketa_poll_error').append('<div id="anketa_error_empty_answer" class="anketa_error_line">'+ $('div#js_answer_not_filled').text() + '</div>');                          
        
            if($('#anketa_poll_error').html() == "") {
                $('#anketa_poll_error').hide();
                return true;
            }
            else {
                $('#anketa_poll_error').show();            
                return false;
            }
                
        }
        
        
        /**
     * checks the whole question, calls another checking functions
     */
        function checkQuestion(obj){
            var errorLabel = false;
    
            if(!checkQuestionText($(obj)))errorLabel = true;             
            if(!checkFewAnswers($(obj))) errorLabel = true;
            if(!checkAnswers($(obj))) errorLabel = true;            
            if(!checkFewAnswersEdit($(obj))) errorLabel = true;            
         
            if(errorLabel) {
                $(obj).find('.anketa_question_label').addClass('anketa_empty_question');
                return false;
            }
            else {
                $(obj).find('.anketa_question_label').removeClass('anketa_empty_question');
                return true;
            }
    
 
        }
        /**
     * checks if the text of question is not empty
     */
        function checkQuestionText(obj){
   
            if($.trim($(obj).find('.anketa_question_text').val()) == "") {
                $(obj).find('.anketa_question_text').addClass('anketa_empty_textarea');        
                emptyQuestion = true;
                return false;
            }
            else {
                $(obj).find('.anketa_question_text').removeClass('anketa_empty_textarea');
                return true;
            }
    
        }

        /**
     * checks if exists at least two answers by each question 
     * in the create template section
     */
        function checkFewAnswers(obj){        
    
            var type = $(obj).find('.anketa_answer_type').val();
            
            if(type == 'multiple' || type == 'just_one'){
                var size = $(obj).find('div.anketa_answer').size();
            
                if(size < 2) {
                    $(obj).find('a.anketa_create_answer').addClass('anketa_empty_textarea_green');
                    fewAnswers = true;            
                    return false
                }
                else {
                    $(obj).find('a.anketa_create_answer').removeClass('anketa_empty_textarea_green');
                    return true;
                }
            }
            return true;        
        
        }
        /**
     * checks if exists at least two answers by each question 
     * in the Edit template section
     */
        function checkFewAnswersEdit(obj){
            
            var type = $(obj).find('.anketa_temp_answer_type').val();
            if(type == 'multiple' || type == 'just_one'){
                var size = $(obj).find('ul').children('li').size();                
                if(size < 2) {   
                    fewAnswersEdit = true;  
                    return false;
                }
                else return true;
            }
            return true;        
            
        }        
        
        /**
         * checks if all answers are filled
         */
        function checkAnswers(obj){
            
            var oneIsEmpty = false;
            $(obj).find('.anketa_create_answer_text').each(function(index){
            
                if($(this).val() == ""){
                    emptyAnswer = true;
                    oneIsEmpty = true;            
                    $(this).addClass('anketa_empty_textarea');          
                }
                else {
                    $(this).removeClass('anketa_empty_textarea');                        
                }
            
            });
            return !oneIsEmpty;
        }
    
        /**
         * checks if at least one page was chosen (in the file tree)
         */
        function checkIfChosenWhere(){        
            return $(".anketa_where_insert").is(":checked") == true;                        
        } 
        
        
        /**
         * checks if the poll/template name is not empty and if the name is correct 
         * and if exists some poll/template with the same name
         */
        function checkName(type, loadAjax){
            
            if($.trim($('input[name='+type+']').val()) == ""){                           
                $('input[name='+type+']').parent('label').addClass('anketa_empty_question');
                $('input[name='+type+']').addClass('anketa_empty_textarea');
                if($('#anketa_error_empty_'+type+'').size() < 1) $('#anketa_poll_error').append('<div id="anketa_error_empty_'+type+'" class="anketa_error_line">'+ $('div#js_empty_'+type).text() +'</div>');                              
                $('div#anketa_error_'+type+'_regexp').hide();
                $('div#anketa_error_'+type).hide();
                $('#anketa_poll_error').show();
                return false;
            }
            
            $('input[name='+type+']').parent('label').removeClass('anketa_empty_question');
            $('input[name='+type+']').removeClass('anketa_empty_textarea');
            $('#anketa_error_empty_'+type+'').remove();
            if($('#anketa_poll_error').html() == "") 
                $('#anketa_poll_error').hide();     
                
            var pattern = new RegExp('^[A-Za-z0-9_-]*$'); 
            if(!pattern.test($.trim($('input[name='+type+']').val()))){
                
                $('input[name='+type+']').parent('label').addClass('anketa_empty_question');
                $('input[name='+type+']').addClass('anketa_empty_textarea');
                if($('#anketa_error_'+type+'_regexp').size() < 1) $('#anketa_poll_error').append('<div id="anketa_error_'+type+'_regexp" class="anketa_error_line">'+ $('div#js_wrong_'+type).text() +'</div>');              
                $('div#anketa_error_'+type+'_exists').hide();
                $('#anketa_poll_error').show();      
                return false;
            }
            
            $('input[name='+type+']').parent('label').removeClass('anketa_empty_question');
            $('input[name='+type+']').removeClass('anketa_empty_textarea');
            $('#anketa_error_'+type+'_regexp').remove();
            if($('#anketa_poll_error').html() == "") 
                $('#anketa_poll_error').hide();   
         
            if(!loadAjax && errorName){
                $('#anketa_poll_error').append('<div id="anketa_error_'+type+'" class="anketa_error_line">'+ $('div#js_'+type+"_exists").text() +'</div>');                                      
                $('input[name='+type+']').addClass('anketa_empty_textarea');
                $('input[name='+type+']').parent('label').addClass('anketa_empty_question');
                $('#anketa_poll_error').show();                    
                return;
            }
             
            $.ajax({
                type: "POST",         
                url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                async: false,
                cache: false,
                data: {                        
                    "action": "check_"+type, 
                    "id": $.trim($('input[name='+type+']').val())                        
                },
            
                success: function(data){                     
                        
                    if(data == "true"){
                        if($('#anketa_error_'+type).size() < 1)
                            $('#anketa_poll_error').append('<div id="anketa_error_'+type+'" class="anketa_error_line">'+ $('div#js_'+type+"_exists").text() + '</div>');                                      
                        $('input[name='+type+']').addClass('anketa_empty_textarea');
                        $('input[name='+type+']').parent('label').addClass('anketa_empty_question');
                        $('#anketa_poll_error').show();                    
                        errorName = true;
                    }
                    else {
                        $('input[name='+type+']').removeClass('anketa_empty_textarea');
                        $('input[name='+type+']').parent('label').removeClass('anketa_empty_question');
                        $('#anketa_error_'+type+'').remove();
                        if($('#anketa_poll_error').html() == "") $('#anketa_poll_error').hide();                                   
                        errorName = false;
                    }                 
                } 
            });         
            return true;
        }    
    
        /**
         * checks if page already contains instance of another poll
         */
        function alreadyContainsPoll(obj, path){
            
            $.ajax({
                type: "POST",         
                url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                cache: false,
                data: {
                    "action": "check_file", 
                    "path": path,
                    "poll_id" : $('#ChoosePollForm-poll_id').val(),
                    "current_lang" : $('#current_lang').val()
                },
            
                success: function(data){                     
                    var buttonsOpts = {};
                    buttonsOpts[$('div#js_no').text()] = function (){ 
                        $(obj).attr('checked', false);
                        $(this).dialog("close"); 
                    }
                    buttonsOpts[$('div#js_yes').text()] = function (){    
                        $(this).dialog("close");                                            
                    }
                    if(data == "false") return;                                                           
                    else $("#anketa_dialog_confirm").text(data);                    
                        
                    $("#anketa_dialog_confirm").dialog({
                        modal: true,
                        resizable : false,
                        position: 'center',
                        dialogClass: 'anketa_confirm anketa_dialog',
                        buttons: buttonsOpts,
                        closeOnEscape: false                            
                    });
                }
            });                
        }
        
        function hideShow(par){
            if(par == "zobrazit obsah otázky") return "skrýt obsah otázky";
            if(par == "skrýt obsah otázky") return "zobrazit obsah otázky";
            if(par == "hide question content") return "show question content";
            if(par == "show question content") return "hide question content";
            return par;
        }
    });  

} ) ( jQuery );