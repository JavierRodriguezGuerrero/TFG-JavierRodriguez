import { AfterViewInit, Component,ElementRef, QueryList, ViewChild,ViewChildren } from '@angular/core';

declare const bootstrap: any;

@Component({
  selector: 'app-inicio',
  imports: [],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent implements AfterViewInit {
  @ViewChild('carousel', { static: true })
  carousel!: ElementRef<HTMLDivElement>;

  @ViewChild('firstVideo', { static: true })
  firstVideo!: ElementRef<HTMLVideoElement>;

  @ViewChildren('videoPlayer')
  videoPlayers!: QueryList<ElementRef<HTMLVideoElement>>;

  carouselInstance!: any;

  ngAfterViewInit(): void {
    
    this.carouselInstance = new bootstrap.Carousel(
      this.carousel.nativeElement,
      { interval: false }
    );

    
    this.firstVideo.nativeElement.addEventListener(
      'loadedmetadata',
      () => {
        this.firstVideo.nativeElement
          .play()
          .catch((err: any) =>
            console.error('Error autoplay primer vídeo:', err)
          );
      }
    );
    
    this.firstVideo.nativeElement.load();
    this.firstVideo.nativeElement.muted = true;

    
    this.carousel.nativeElement.addEventListener(
      'slid.bs.carousel',
      () => {
        
        this.videoPlayers.forEach(v => v.nativeElement.pause());
        this.videoPlayers.forEach(v => (v.nativeElement.muted = true));

       
        const el = this.carousel.nativeElement.querySelector(
          '.carousel-item.active video'
        );
        if (el instanceof HTMLVideoElement) {
          el
            .play()
            .catch((err: any) =>
              console.error('Error autoplay vídeo slide:', err)
            );
        }
      }
    );
  }

  public nextSlide(): void {
    this.carouselInstance.next();
  }

  public prevSlide(): void {
    this.carouselInstance.prev();
  }
}