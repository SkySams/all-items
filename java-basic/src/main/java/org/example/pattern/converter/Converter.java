//package org.example.pattern.converter;
//
//import lombok.RequiredArgsConstructor;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
///**
// * @author: zyh
// * @date: 2022/8/9
// */
//@RequiredArgsConstructor
//public class Converter <T,U>{
//
//    private final Function<T,U> fromDto;
//    private final Function<U,T> fromEntity;
//
//    /**
//     * Converts DTO to Entity.
//     * @param dto
//     * @return
//     */
//    public final U convertFromDto(final T dto){
//        return fromDto.apply(dto);
//    }
//
//    public final T convertFromEntity(final U entity) {
//        return fromEntity.apply(entity);
//    }
//
//    public final List<U> createFromDtos(final Collection<T> dtos) {
//        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
//    }
//
//    public final List<T> createFromEntities(final Collection<U> entities) {
//        return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
//    }
//
//
//}
