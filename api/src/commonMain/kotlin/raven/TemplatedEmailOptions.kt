package raven

import identifier.Brand

class TemplatedEmailOptions<T>(
    val factory: (params: FactoryParams, input: T, brand:Brand?) -> SendEmailTemplateParams
)