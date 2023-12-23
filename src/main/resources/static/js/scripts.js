window.addEventListener('DOMContentLoaded', event => {
    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        //
        
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

    //Activar NavsLinks
    var pathname = window.location.pathname;
    var allNavButtons = document.querySelectorAll('.nav-link');
    var navsToActive = Array.from(allNavButtons).filter(n => n.getAttribute('href') === pathname);
    var navsToActive = Array.from(allNavButtons).filter(n => n.getAttribute('href') === pathname);
    navsToActive.forEach((n) => {
        n.classList.add('active');
        if (n.parentNode?.classList.contains('sb-sidenav-menu-nested')) {
            n.parentNode.parentNode?.classList.add('show');
            n.parentNode.parentNode?.parentNode?.classList.remove('collapsed');
        }
    });

});


hideSpinner = () => {
    sprinner.style.opacity = '0';
    setTimeout(() => {
        sprinner.style.display = 'none';
    }, 200);
}

showSpinner = () => {
    sprinner.style.opacity = '100';
    setTimeout(() => {
        sprinner.style.display = '';
    }, 200);
}

//Loader
const sprinner = document.querySelector('.spinner-wrapper');
window.addEventListener('load', hideSpinner);
window.addEventListener('beforeunload', showSpinner);


$.ithReq = function (options) {
    showSpinner();
    var defaults = {
        url: '',
        type: 'POST',
        data: {},
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
            hideSpinner();
        },
        error: function (data) {
            console.error(data);
            hideSpinner();
        }
    };

    var settings = $.extend({}, defaults, options);

    $.ajax(settings);
}

modal = {
    success : function(options) {
        let defaults = {
            title: '<i class="fa-solid fa-circle-check text-success"></i> Resultado exitoso',
            centerVertical: true,
            buttons: {
                ok: {
                    label: 'Ok', 
                    className: 'btn btn-success'
                }
            }
        }
        var settings = $.extend({}, defaults, options);
        bootbox.dialog(options)
    },
    error : function(options) {
        let defaults = {
            title: '<i class="fa-solid fa-circle-exclamation text-danger"></i> Ocurrió un error inesperado',
            centerVertical: true,
            buttons: {
                ok: {
                    label: 'Ok', 
                    className: 'btn btn-secondary'
                }
            }
        }
        var settings = $.extend({}, defaults, options);
        bootbox.dialog(options)
    }
}

