import kollections.listOf
import kommander.expect
import kommander.expectFailure
import koncurrent.FailedLater
import koncurrent.Later
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import raven.EmailContentType
import raven.EmailSender
import raven.MultiEmailSender
import raven.SendEmailParams
import kotlin.test.Test

class MultiEmailSenderTest {


    @Test
    fun should_report_errors_properly_even_when_senders_fail() = runTest {
        val sender = MultiEmailSender(listOf())
        val params = SendEmailParams(
            from = "sender@test.com",
            to = "receiver@test.com",
            subject = "Test Email",
            body = "This is just a test email"
        )
        val exception = expectFailure { sender.send(params).await() }
        expect(exception.message).toBe("raven.exceptions.MailingException: No mailer has been added to a multi sender email")
    }

    class ErroneousSender : EmailSender {
        override fun supports(body: EmailContentType): Boolean = true

        override fun send(params: SendEmailParams): Later<SendEmailParams> = FailedLater("Failing for fun")
    }

    @Test
    fun should_report_the_error_even_when_there_is_one_error() = runTest {
        val sender = MultiEmailSender(listOf(ErroneousSender()))
        val params = SendEmailParams(
            from = "sender@test.com",
            to = "receiver@test.com",
            subject = "Test Email",
            body = "This is just a test email"
        )
        val exception = expectFailure { sender.send(params).await() }
        expect(exception.message).toBe("raven.exceptions.MailingException: Failing for fun")
    }
}