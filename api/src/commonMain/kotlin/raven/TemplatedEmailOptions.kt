package raven

class TemplatedEmailOptions(
    val params: (to: Address, link: String) -> SendEmailTemplateParams
)