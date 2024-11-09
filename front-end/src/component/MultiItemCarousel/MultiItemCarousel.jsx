import React from 'react';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import Slider from 'react-slick';
import { topMeels } from '../../Data/topMeels';
import { CarouselItem } from './CarouselItem';
export const MultipleItemsCarousel = () => {
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    pauseOnHover: true,
    arrows: false,
  };
  return (
    <div>
      <Slider {...settings}>
        {/* cái ...setting này là sao? coi kĩ */}
        {topMeels.map((item) => (
          <CarouselItem image={item.image} title={item.title} />
        ))}
      </Slider>
    </div>
  );
};
