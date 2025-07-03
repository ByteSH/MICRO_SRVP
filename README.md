# MICRO-SRVP



////        return product.stream()
////                .map(single_product -> getProductResponse(single_product))
////                .toList();
//
//        return product.stream()
//                .map(this::getProductResponse)
//                .toList();

    }

    private ProductResponse getProductResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
