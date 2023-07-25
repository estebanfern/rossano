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
    });

});
