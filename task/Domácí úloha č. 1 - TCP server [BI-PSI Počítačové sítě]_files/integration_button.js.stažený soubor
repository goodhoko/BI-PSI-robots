(function($){

    //<![CDATA[
    document.write('\
		<style type="text/css">\
            @font-face {\
                font-family: \'icomoon\';\
                src: url(data:application/x-font-ttf;charset=utf-8;base64,AAEAAAALAIAAAwAwT1MvMg8SDTQAAAC8AAAAYGNtYXAQS+BrAAABHAAAAExnYXNwAAAAEAAAAWgAAAAIZ2x5ZhYQkjoAAAFwAAACNGhlYWQFOCztAAADpAAAADZoaGVhB8IDxgAAA9wAAAAkaG10eAYAAAAAAAQAAAAAFGxvY2EAKAEuAAAEFAAAAAxtYXhwAA8AvwAABCAAAAAgbmFtZVcZpu4AAARAAAABRXBvc3QAAwAAAAAFiAAAACAAAwQAAZAABQAAApkCzAAAAI8CmQLMAAAB6wAzAQkAAAAAAAAAAAAAAAAAAAABEAAAAAAAAAAAAAAAAAAAAABAAADwCgPA/8AAQAPAAEAAAAABAAAAAAAAAAAAAAAgAAAAAAACAAAAAwAAABQAAwABAAAAFAAEADgAAAAKAAgAAgACAAEAIPAK//3//wAAAAAAIPAK//3//wAB/+MP+gADAAEAAAAAAAAAAAAAAAEAAf//AA8AAQAAAAAAAAAAAAIAADc5AQAAAAABAAAAAAAAAAAAAgAANzkBAAAAAAEAAAAAAAAAAAACAAA3OQEAAAAACQAAAEkEAANuABQAKQA+AFMAaAB9AJIApwC8AAAlFRQHBisBIicmPQE0NzY7ATIXFhURFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhUBFRQHBisBIicmPQE0NzY7ATIXFhURFRQHBisBIicmPQE0NzY7ATIXFhUBJRAQF7cXEBAQEBe3FxAQEBAXtxcQEBAQF7cXEBABbRAQF7YXEBAQEBe2FxAQ/pMQEBe3FxAQEBAXtxcQEAFtEBAXthcQEBAQF7YXEBABbhAQF7cXEBAQEBe3FxAQ/pIQEBe2FxAQEBAXthcQEAFuEBAXtxcQEBAQF7cXEBAQEBe3FxAQEBAXtxcQEO5uFxAQEBAXbhcQEBAQFwEkbRcQEBAQF20XEBAQEBf+3G4XEBAQEBduFxAQEBAXAkluFxAQEBAXbhcQEBAQF/7bbRcQEBAQF20XEBAQEBf+3G4XEBAQEBduFxAQEBAXAkluFxAQEBAXbhcQEBAQF/7bbRcQEBAQF20XEBAQEBcBJW4XEBAQEBduFxAQEBAXAAAAAQAAAAEAAP69X6pfDzz1AAsEAAAAAADRDPRBAAAAANEM9EEAAAAABAADbgAAAAgAAgAAAAAAAAABAAADwP/AAAAEAAAAAAAEAAABAAAAAAAAAAAAAAAAAAAABQAAAAAAAAAAAAAAAAIAAAAEAAAAAAAAAAAKABQAHgEaAAEAAAAFAL0ACQAAAAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAOAK4AAQAAAAAAAQAOAAAAAQAAAAAAAgAOAEcAAQAAAAAAAwAOACQAAQAAAAAABAAOAFUAAQAAAAAABQAWAA4AAQAAAAAABgAHADIAAQAAAAAACgA0AGMAAwABBAkAAQAOAAAAAwABBAkAAgAOAEcAAwABBAkAAwAOACQAAwABBAkABAAOAFUAAwABBAkABQAWAA4AAwABBAkABgAOADkAAwABBAkACgA0AGMAaQBjAG8AbQBvAG8AbgBWAGUAcgBzAGkAbwBuACAAMQAuADAAaQBjAG8AbQBvAG8Abmljb21vb24AaQBjAG8AbQBvAG8AbgBSAGUAZwB1AGwAYQByAGkAYwBvAG0AbwBvAG4ARgBvAG4AdAAgAGcAZQBuAGUAcgBhAHQAZQBkACAAYgB5ACAASQBjAG8ATQBvAG8AbgAuAAAAAAMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=) format(\'truetype\'),\
                url(data:application/font-woff;charset=utf-8;base64,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) format(\'woff\');\
                font-weight: normal;\
                font-style: normal;\
            }\
            [class^="icon-"], [class*=" icon-"] {\
                font-family: \'icomoon\';\
                speak: none;\
                font-style: normal;\
                font-weight: normal;\
                font-variant: normal;\
                text-transform: none;\
                line-height: 1;\
                -webkit-font-smoothing: antialiased;\
                -moz-osx-font-smoothing: grayscale;\
            }\
            .icon-th:before {\
                content: "\\f00a";\
            }\
			.integration-bar {\
                display: -webkit-box;\
                display: -o-flex;\
                display: -moz-box;\
                display: -ms-flexbox;\
                display: -webkit-flex;\
                display: flex;\
                \
                -webkit-flex-flow: row wrap;\
                -moz-flex-flow: row wrap;\
                -ms-flex-flow: row wrap;\
                -o-flex-flow: row wrap;\
                flex-flow: row wrap;\
                \
                -webkit-justify-content: center;\
                -moz-justify-content: center;\
                -ms-justify-content: center;\
                -o-justify-content: center;\
                justify-content: center;\
			    \
                position: relative;\
                background: #FAFAFA;\
                border: 0.1em solid #EEEEEE;\
                position: absolute;\
                padding: 0.6em;\
                width: 27em;\
                \
                font-size: 1em;\
                box-shadow: -0.3em 0.3em 0.5em #aaa;\
            }\
            .integration-bar > div.arrow {\
                position:absolute;\
                top: -1em;\
                left:50%;\
                margin-left:-1em;\
                width:0;\
                height:0;\
                border-left: 1em solid transparent;\
                border-right: 1em solid transparent;\
                border-bottom: 1em solid #eee;\
            }\
            .tile {\
                -webkit-flex: 0 1 auto;\
                -moz-flex: 0 1 auto;\
                -ms-flex: 0 1 auto;\
                -o-flex: 0 1 auto;\
                flex: 0 1 auto;\
                \
                border: 0.1em solid transparent;\
                width: 8em;\
                font-family: sans-serif;\
                margin: 0.2em;\
                padding: 1em;\
                text-decoration: none;\
                color: #696969;\
                vertical-align:top;\
            }\
            .tile:hover {\
                border: 0.1em solid #eee\
            }\
            .tile > span {\
                display: block;\
            }\
            .tile > span.icon {\
                text-align: center;\
                font-size: 2em;\
            }\
            .tile > span.icon span.tile-img {\
                overflow:hidden;\
            }\
            .tile > span.icon span.tile-img img {\
                vertical-align: middle;\
                max-height: 1em;\
            }\
            .tile > span.title {\
                padding: 0.8em 0 0 0;\
                text-align: center;\
            }\
		</style>\
	');
    //]]>

    function IntegrationButton(options)
    {
        var defaults = {
            $buttonElement: null,

            dataUrl: 'https://ict.fit.cvut.cz/integration/data.jsonp',
            extension: '',
            file: '',

            locale: 'cs',
            buttonDescription: '',
            selector: '.integration-button',
            initButton: true
        };
        this.options = $.extend({}, defaults, options || {});

        if (this.options.file) {
            this.options.dataUrl += this.options.file;
        }

        if (this.options.extension) {
            this.options.dataUrl += '.' + this.options.extension;
        }

        if (!this.options.$buttonElement) {
            throw "Button element must be specified $buttonElement";
        }

        this.selector = options.selector;
    }

    IntegrationButton.prototype.initButtonSheet = function() {
        if (!this.options.initButton)
            return;

        $('<span><i class="icon-th"></i>' + this.options.buttonDescription + '</span>').appendTo(this.options.$buttonElement);
    };

    IntegrationButton.prototype.register = function () {
        if (!this.$element) {
            throw "You must call render first!";
        }

        var visible = false;

        var that = this;
        this.options.$buttonElement.bind('click', function (e) {
            e.preventDefault();
            if (!visible)
                $(that.$element).trigger('show');
                that.$element.fadeIn(function () {
                    visible = true;
                });
        });

        if (typeof $.fn.on === 'function') {
            $(document).on('click', function (e) {
                if (visible && !that.$element.is(e.target) && !that.$element.children().is(e.target)) {
                    that.$element.fadeOut(function () {
                        visible = false;
                    });
                }
            });
        } else {
            this.options.$buttonElement.bind('click', function (e) {
                e.preventDefault();
                if (visible)
                    that.$element.fadeOut(function () {
                        visible = false;
                    });
            });
        }
    };

    IntegrationButton.prototype.createTile = function (data) {
        var locale = this.options.locale;
        var iconElement = '<i class="icon-th"></i>';
        var title;

        if (typeof data.title === 'undefined' || typeof data.href === 'undefined') {
            console.error('Some mandatory parameters missing.');
            return;
        }

        if (typeof data.title === 'object') {
            if (typeof data.title[locale] === 'undefined') {
                console.error('Title in locale be specified');
                return;
            }
            title = data.title[locale];
        } else {
            title = data.title;
        }

        if (typeof data['icon-class'] !== 'undefined') {
            iconElement = '<i class="' + data['icon-class'] + '"></i>';
        }

        if (typeof data['icon-href'] !== 'undefined' || typeof data['icon-base64'] !== 'undefined') {
            if (typeof data['icon-href'] !== 'undefined') {
                iconElement = '<span class="tile-img"><img src="' + data['icon-href'] + '" /></span>';
            }

            if (typeof data['icon-base64'] !== 'undefined') {
                iconElement = '<span class="tile-img"><img src="' + data['icon-base64'] + '" /></span>';
            }
        }

        var tileTemplate =
                '<a class="tile" href="' + data.href + '">' +
                '<span class="icon">' + iconElement + '</span>' +
                '<span class="title">' + title + '</span>' +
                '</a>'
            ;
        return $(tileTemplate).appendTo(this.$element);
    };

    IntegrationButton.prototype.render = function ($container) {
        this.initButtonSheet();

        this.$element = this.$element || null;
        if (this.$element !== null) {
            this.$element.remove();
        }

        this.$element = $('<div class="integration-bar"><div class="arrow"></div></div>');
        this.$element.css('display', 'flex');

        var that = this;

        var callback = function (data) {
            if (typeof data['services'] !== 'undefined') {
                $.each(data.services, function () {
                    that.createTile(this);
                });
            } else {
                console.error("No data fetched from server.")
            }
        };

        if (this.options.dataUrl.match(/.jsonp$/)) {
            $.ajax({
                url: this.options.dataUrl,
                type: 'GET',
                dataType: 'jsonp',
                jsonp: 'callback',
                jsonpCallback: 'jsonpCallback',
                success: callback
            });
        } else {
            $.getJSON(this.options.dataUrl, callback);
        }

        this.$element.appendTo("body");
        (function (){
            var update = function ($arrowElement, $buttonElement){
                var position        = $buttonElement.position();
                var middle          = this.outerWidth() / 2;
                var left            = position.left + $buttonElement.outerWidth() / 2 - middle;
                var originalLeft    = left;
                //var windowWidth     = ($(window).width() > $(document).width()) ? $(window).width() : $(document).width();
                var windowWidth     = $(window).width();
                var offset          = 0;

                if (left + this.outerWidth() > $(window).width()) {
                    left = windowWidth - this.outerWidth();
                }

                if (left < 0) {
                    left = 0;
                }

                offset = originalLeft - left;

                if (Math.abs(offset) > middle) {
                    offset = offset / Math.abs(offset) * middle;
                }

                this.css('top', position.top + $buttonElement.outerHeight() + 10);
                this.css('left', left);

                $arrowElement.css('left', middle + offset);
            };
            var bindedUpdate = update.bind(this.$element, this.$element.find('.arrow'), this.options.$buttonElement);

            $(window).bind('resize', function () {
                bindedUpdate();
            });
            $(this.$element).bind('show', function () {
                bindedUpdate();
            });
            bindedUpdate();
        }).call(this);
        this.$element.hide();
    };

    $.fn.integrationButton = function (options) {
        if ($(this).length < 1) {
            throw "Selected element does not exists.";
        }

        options = options || {};
        options.selector = this.selector;
        options.$buttonElement = $(this);

        var bar = new IntegrationButton(options);

        bar.render($(this));
        bar.register();
    };
})(jQuery);