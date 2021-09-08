package com.rptp.rptpSpringBoot.controller.api.common.documentation;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class PostDocumentation {
    public static RestDocumentationResultHandler getPuppies() {
        return document("puppy/findAll",
                requestFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("id"),
                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("name"),
                        fieldWithPath("[].age").type(JsonFieldType.NUMBER).description("age"),
                        fieldWithPath("[].breed").type(JsonFieldType.STRING).description("breed"),
                        fieldWithPath("[].vetId").type(JsonFieldType.NUMBER).description("vetId"),
                        fieldWithPath("[].vetName").type(JsonFieldType.STRING).description("vetName")
                ));
    }

    public static RestDocumentationResultHandler getPuppy() {
        return document("puppy/find",
                pathParameters(
                        parameterWithName("id").description("puppy id")
                ),
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                        fieldWithPath("age").type(JsonFieldType.NUMBER).description("age"),
                        fieldWithPath("breed").type(JsonFieldType.STRING).description("breed"),
                        fieldWithPath("vetId").type(JsonFieldType.NUMBER).description("vetId"),
                        fieldWithPath("vetName").type(JsonFieldType.STRING).description("vetName")
                ));
    }

}
