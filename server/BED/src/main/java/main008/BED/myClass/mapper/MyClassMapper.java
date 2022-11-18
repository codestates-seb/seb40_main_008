package main008.BED.myClass.mapper;

import main008.BED.myClass.dto.MyClassDto;
import main008.BED.myClass.entity.MyClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyClassMapper {

    MyClassDto.WishClassResponse myClassToWishResponse(MyClass myClass);

    MyClassDto.TakingClassResponse myClassToTakingResponse(MyClass myClass);
}
