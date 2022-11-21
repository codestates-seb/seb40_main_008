export interface Carousel {
  carouselInfo: CarouselInfo[];
}

export interface CarouselInfo {
  carouselId: number;
  imageUrl: string;
  title: string;
  subTitle: string;
  redirectUrl: string;
}
