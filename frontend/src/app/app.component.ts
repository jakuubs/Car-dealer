import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Car dealer';

  constructor(private router: Router, private titleService: Title) {
    this.router.events.subscribe((e) => {
      if (e instanceof NavigationEnd) {
        this.markSelectedTab();
      }
   });
   this.titleService.setTitle(this.title);
  }

  markSelectedTab() {
    var elements = Array.from(document.getElementsByClassName('tab'));
    elements.forEach(element => {
      if (element.getAttribute('routerLink') === this.router.url) {
        element.setAttribute('class', 'tab nav-link active');
        console.warn(element.getAttribute('routerLink'));
        console.warn(this.router.url);
      } else {
        element.setAttribute('class', 'tab nav-link');
      }
    })
  }
}
