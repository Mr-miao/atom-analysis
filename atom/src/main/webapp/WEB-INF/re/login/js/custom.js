
 /* jQuery Pre loader
  -----------------------------------------------*/
$(window).load(function(){
    $('.preloader').fadeOut(1000); // set duration in brackets    
});


$(document).ready(function() {

  /* Home Slideshow Vegas
  -----------------------------------------------*/
  $(function() {
    $('body').vegas({
        slides: [
           /* { src: 'images/slide-1.jpg' },
            { src: 'images/slide-2.jpg' },*/		
			{ src: webPath+'re/login/images/slide-3.jpg' },	
			{ src: webPath+'re/login/images/slide-4.jpg' }
					
        ],
        timer: false,
        transition: [ 'zoomOut', ]
    });
  });


  /* wow
  -------------------------------*/
  new WOW({ mobile: false }).init();

  });

