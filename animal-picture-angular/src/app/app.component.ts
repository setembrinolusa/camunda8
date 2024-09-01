import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PicturesComponent } from './pictures/pictures.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PicturesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'animal-picture-angular';
}
