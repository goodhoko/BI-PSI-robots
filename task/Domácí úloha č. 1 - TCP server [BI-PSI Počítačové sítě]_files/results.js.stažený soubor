
( function($) {
    
    google.load('visualization', '1.0', {
        'packages':['corechart']
    });           
        
    $(document).ready(function() {    
    
        // lodas the filled in fields
        if($('form#ResultsForm').size() > 0){
            var resultsSent = false;
            var locations = new Array();
            $('input[name=poll_locations\[\]]:checked').each(function(index, value){        
                locations[index] = $(this).val();            
            });        
        
            var parallels= new Array();
            $('input[name=parallels\[\]]:checked').each(function(index, value){        
                parallels[index] = $(this).val();            
            });
            var templateID = $('select[name=poll_id]').val();
            var aspect = $('select[name=aspect]').val();        
        }
        
        $('a.anketa_toggle_answers').click(function(event){
    
            $(this).parents(".anketa_poll_question_results").next('div.anketa_answers_body').slideToggle('medium');
            $(this).text(hideShowResults($(this).text()));
            event.preventDefault();     
        });
        
        $('a.anketa_toggle_answers_syntax').live('click',function(event){
            
            $(this).parents(".anketa_poll_question_results_syntax").next('div.anketa_answers_body').slideToggle('medium');
            $(this).text(hideShowResults($(this).text()));            
            event.preventDefault(); 
   
        });
        
        $('a.anketa_toggle_answers_syntax_own').live('click',function(event){
            
            $(this).parents(".anketa_poll_question_results_syntax").next('div.anketa_answers_body_hidden').slideToggle('medium');
            $(this).text(hideShowResults($(this).text()));           
            event.preventDefault();              
            
        });
     
        $('.anketa_toggle_own_answers').click(function(event){
            $(this).parent("div.anketa_filename_headline").next('div.anketa_own_answers_body').slideToggle('medium');    
            event.preventDefault();  
        });
        
        $('.anketa_check_all').live('click',function(event){
            $(this).parent('p').parent('fieldset').find('input').attr('checked',true);            
            if(resultsSent) checkCheckboxes(event);
            event.preventDefault(); 
        });
        $('.anketa_check_all_parallels').live('click',function(event){
            $(this).parent('p').parent('div').find('input').attr('checked',true);            
            if(resultsSent) checkCheckboxes(event);
            event.preventDefault(); 
        });
        
        
        $('.anketa_check_none').live('click',function(event){
            $(this).parent('p').parent('fieldset').find('input').attr('checked',false);            
            if(resultsSent) checkCheckboxes(event);
            event.preventDefault(); 
        });
        
        $('.anketa_check_none_parallels').live('click',function(event){
            $(this).parent('p').parent('div').find('input').attr('checked',false);            
            if(resultsSent) checkCheckboxes(event);
            event.preventDefault(); 
        });
        
        
        /// loads the data using AJAX
        function getData(section, question, answer){
          
            function Column(location, votes, total){
                this.loc = location;
                this.count = votes;
                this.total = total;              
            }
                    
            $.ajax({
                type: "POST",              
                cache: false,
                url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                data: {
                    "action": "graph", 
                    "poll_id": templateID, 
                    "poll_locations[]": locations, 
                    "parallels[]": parallels, 
                    "aspect": aspect, 
                    "section": section, 
                    "question_id": question, 
                    "answer_id": answer,
                    "current_lang" : $('#current_lang').val()
                },
            
                success: function(data){  
                    // there was an error loading data
                    if(data == "false"){ 
                        $('#anketa_chart_div').text($('div#js_results_not_found').text());
                        return;
                    }
                    var dataArray = new Array();              
                    var lines = data.split("\n");              
                    for(i = 0; i < lines.length-1; i++){                  
                        var col = lines[i].split(":");                  
                        if(isNaN(parseInt(col[1]))) col[1] = 0;
                        if(isNaN(parseInt(col[2]))) col[2] = 0;                        
                        dataArray[i] = new Column(col[0], parseInt(col[1]), parseInt(col[2]));              
                    }                            
                    drawChart(dataArray);
                }
            });           


        }
    
        /**
         * sets options and draws the graph
         */
        function drawChart(dataArray) {

            // Createss the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', '');
            data.addColumn('number', $('div#js_translate_votes').text());
            data.addColumn('number', $('div#js_translate_voters').text());      
      
            for(i = 0; i < dataArray.length ; i++){          
                data.addRows([[dataArray[i].loc, dataArray[i].count, dataArray[i].total]]);    
            }
        
            // Sets chart options
            var options = {                
                'width': '100%',                
                'height': (dataArray.length)  * 50 + 60,
                'colors': ['blue', 'lightgrey'],                                
                'legend': 'top',
                chartArea: {
                    width: '65%'
                }
                
            };
            $('#anketa_chart_div').css('max-height', $(window).height()*0.65); 
            // Instantiates and draws our chart, passing in some options.
            var chart = new google.visualization.BarChart($('#anketa_chart_div')[0]);
            chart.draw(data, options);
            
        }
    
        $('a.anketa_show_graphs').click(function(event){
            var temp = $(this).parent('td').prev('td').text();            
            
            attr = temp.split("_");            
            var section = attr[0];
            var question = attr[1]; 
            var answer = attr[2];           
            
            event.preventDefault();     
            $('#anketa_chart_info').html('<p>' + $('div#js_translate_question').text() +': ' + 
                $(this).parents('div.anketa_answers_body').prev('div.anketa_poll_question_results').find('span.anketa_question_label_results').text() + "</p><p>" +
                $('div#js_translate_answer').text() + ': ' + $(this).parent('td').parent('tr').find('td.anketa_poll_answer').text() + "</p>"
                );
            getData(section, question, answer);
            var jdialog = $('#anketa_dialog_display_graphs').dialog({                
                autoOpen: false,   
                dialogClass: 'anketa_graphs anketa_dialog',                                
                modal: true,
                draggable: false,
                resizable: false,
                open: function(event,ui){                    
                
                    $('.ui-widget-overlay').bind('click',function(){                
                        $('#anketa_dialog_display_graphs').dialog('close');
                    });
                    $(event.target).parent().css({
                        'max-height': $(window).height()*0.8,                         
                        'position' : 'fixed',
                        'top' : '5%',
                        'overflow-y': 'auto'                        
                    });                     
                }                  
            });            
            jdialog.dialog('open');            
        
        });
        
        $('a.anketa_toggle_all_answers').click(function(event){
            $(this).text(hideShowOwn($(this).text()));
            $(this).parents('div.anketa_poll_question_results').next('div.anketa_answers_body').find('div.anketa_own_answers_body').toggle('slow');                  
       
            event.preventDefault();
       
        });
    
        /// updates the list of poll instances using AJAX
        $('select#ResultsForm-poll_id').change(function(event){
        
            $.ajax({
                type: "POST",    
                cache: false,
                url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                data: {
                    "action": "update_instances", 
                    "poll_id": $.trim($(this).val()), 
                    "current_lang" : $('#current_lang').val()
                },            
                success: function(data){                                    
                    $('#instances_body').html(data);                                
                }                  
            });                           
        });
    
        /// displays the results in the public section
        $('select[name=results_parallel]').change(function(event){        
        
            if($(this).val()=="none") $('#anketa_results').html("");        
            else{
                $.ajax({
                    type: "POST",    
                    cache: false,
                    url: DOKU_BASE + "/lib/plugins/anketa/ajax.php",
                    data: {
                        "action": "print_results", 
                        "parallel": $.trim($(this).val()),
                        "poll_id" : $.trim($('#poll_id').val()), 
                        "instance_id" :  $.trim($('#instance_id').val()),                  
                        "current_lang" : $('#current_lang').val()
                    },
            
                    success: function(data){                    
                        $('#anketa_results').html(data);                                
                    }                  
                });           
            }        
        
        });
        
        // validation of results form
        function checkCheckboxes(event){
            var error = false;
            $('p#anketa_error_voting').remove(); 
        
            $('fieldset.anketa_poll_results_options').each(function(){
                if(!$(this).find("input").is(":checked")){
                    error = true;
                    $(this).addClass('anketa_empty_textarea');
                }
                else $(this).removeClass('anketa_empty_textarea');
            });
        
        
            if(error) {
                $('#ResultsForm-display_results').before('<p id="anketa_error_voting">'+ $('div#js_results_error_filling').text()+ '</p>') ;
                event.preventDefault();    
            }
        }
        $('fieldset.anketa_poll_results_options').live('change',function(event){        
            if(resultsSent) checkCheckboxes(event);
        });
    
    
        $('form#ResultsForm').submit(function(event){
            checkCheckboxes(event);         
            resultsSent = true;
        });
    
        function hideShowResults(par){
            if(par == "zobrazit výsledky") return "skrýt výsledky";
            if(par == "skrýt výsledky") return "zobrazit výsledky";
            if(par == "hide results") return "show results";
            if(par == "show results") return "hide results";
            return par;
        }
        function hideShowOwn(par){
            if(par == "rozbalit všechny odpovědi") return "sbalit všechny odpovědi";
            if(par == "sbalit všechny odpovědi") return "rozbalit všechny odpovědi";
            if(par == "unwrap all own answers") return "wrap all own answers";
            if(par == "wrap all own answers") return "unwrap all own answers";
            return par;
        }
    
    }); // end of document.ready

} ) ( jQuery );