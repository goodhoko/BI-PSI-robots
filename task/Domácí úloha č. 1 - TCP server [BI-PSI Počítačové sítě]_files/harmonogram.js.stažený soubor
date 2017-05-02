// Datepickers.
jQuery(function() {
	jQuery('#BasicForm-semester_start, #BasicForm-semester_end, #Daysoff-semester_dayoff_date, #MovesForm-semester_move_date').datepicker({
		firstDay: 1,
		dateFormat: 'dd.mm.yy'
	});
});

var DragNDrop = {
	/**
	 * Initialize.
	 */
	init: function() {
		DragNDrop.bindTrash();
		DragNDrop.bindDraggable();
		DragNDrop.bindDroppable();
		DragNDrop.bindSortable();
		DragNDrop.bindScrollWithPage();
	},

	/**
	 * Binds onclick actions on trashes.
	 */
	bindTrash: function() {
		jQuery(".trash").livequery("click", function() {
			jQuery(this).parent().remove();
		});
	},

	/**
	 * Binds draggable options from option list.
	 */
	bindDraggable: function() {
		jQuery("#option-list .option").draggable({
			revert: 'invalid',
			helper: 'clone',
			opacity: 0.8
		});
	},

	/**
	 * Binds sortable to options in the boxes. They can be moved from box to box.
	 */
	bindSortable: function() {
		jQuery(".box.normal .option-list").disableSelection().sortable({
			connectWith: '.box.normal .option-list',
			axis: 'y',
			placeholder: 'ui-state-highlight',
			delay: 3,
			opacity: 0.8,
			tolerance: "pointer",
			receive: function(event, ui) {
				var uniqueItems = DragNDrop.checkRecivedUnique(ui.item.find(".option"), jQuery(this).find("li"));
				if (!uniqueItems) {
					jQuery(ui.sender).sortable('cancel')
				}
			}
		});
	},

	/**
	 * Binds droppable to boxes.
	 */
	bindDroppable: function() {
		jQuery("#droppable-list .box.normal .dw-subcontent").droppable({
			activeClass: "ui-state-default",
			hoverClass: "ui-state-hover",
			accept: ".option",
			drop: function(event, ui) {
				var uniqueItems = DragNDrop.checkDropedUnique(ui.draggable, jQuery(this).find("li"));
				if (uniqueItems) {
					DragNDrop.appendItem(ui.draggable, jQuery(this).find("ul"));
				}
			}
		});
	},

	/**
	 * Binds scrolling with page to option list.
	 */
	bindScrollWithPage: function() {
		var nodeOffset = jQuery('#option-list').offset();
		if (nodeOffset) {
			var top = nodeOffset.top - parseFloat(jQuery('#option-list').css('marginTop').replace(/auto/, 0));
		}

		jQuery(window).scroll(function (event) {
			// what the y position of the scroll is
			var y = jQuery(this).scrollTop();

			// whether that's below the form
			if (y >= top) {
				// if so, ad the fixed class
				jQuery('#option-list').addClass('fixed');
			} else {
				// otherwise remove it
				jQuery('#option-list').removeClass('fixed');
			}
		});
	},
	
	/**
	 * Counts number of item occurences in list.
	 */
	occurrences: function(item, list) {
		var contains = 0;
		list.each(function() {
			if ((item.text().trim()) == jQuery(this).find(".option").text().trim()) {
				contains++;
			}
		});
		return contains;
	},

	/**
	 * Checking whether droped element already is in the list, or not.
	 */
	checkDropedUnique: function(item, list) {
		if (DragNDrop.occurrences(item, list) > 0) {
			alert('položka zde již je.');
			return false;
		}
		return true;
	},

	/**
	 * Checking whether recived element already is in the list, or not.
	 */
	checkRecivedUnique: function(item, list) {
		if (DragNDrop.occurrences(item, list) > 1) {
			alert('položka zde již je.');
			return false;
		}
		return true;
	},
	
	/**
	 * Appending text to list.
	 */
	appendItem: function(draggable, list) {
		var startTag = "<li><span class=\"option\" alt=\"\" title=\"" + draggable.attr('title') + "\"> ";
		var endTag   = " </span><span class=\"trash\"><span class=\"ui-icon ui-icon-trash\"></span></span></li>";

		var addItem = startTag + draggable.text() + endTag;
		list.append(addItem);
	}
};

var TopicsDragNDrop = {
	init: function() {
		TopicsDragNDrop.bindSubmit();
	},
	bindSubmit: function() {
		jQuery('#EducationEdit').livequery("submit", function () {
			var education = '';
			jQuery('#droppable-list .box.normal').each(function() {
				jQuery(this).find('.option').each(function () {
					education += jQuery(this).attr('title') + ';';
				});
				education = education.replace(/;$/,'');
				education += ',';
			});
			education = education.replace(/,$/,'');

			jQuery('input[name=\'education\']').val(education);
		});
	}
};

// Initialize.
jQuery(DragNDrop.init);
jQuery(TopicsDragNDrop.init);