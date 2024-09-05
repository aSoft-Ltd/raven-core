package raven

class TemplatedEmailOptions<T>(
    val factory: (params: FactoryParams, input: T) -> SendEmailTemplateParams
)