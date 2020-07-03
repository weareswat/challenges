using TechTalk.SpecFlow;

namespace Api.IntegrationTests.Steps
{
    [Binding]
    public class PostSteps
    {
        private readonly ScenarioContext _context;

        public PostSteps(ScenarioContext context)
        {
            _context = context ?? throw new System.ArgumentNullException(nameof(context));
        }

        [Given(@"I have a post with id (.*)")]
        public void GivenIHaveAPostWithId(int postId)
        {
            _context.Add(key: "PostId")
        }

        [Given(@"it contains (.*) up votes")]
        public void GivenItContainsUpVotes(int p0)
        {
            ScenarioContext.Current.Pending();
        }

        [Given(@"it contains (.*) down votes")]
        public void GivenItContainsDownVotes(int p0)
        {
            ScenarioContext.Current.Pending();
        }

        [When(@"I up vote the post")]
        public void WhenIUpVoteThePost()
        {
            ScenarioContext.Current.Pending();
        }

        [When(@"I down vote the post")]
        public void WhenIDownVoteThePost()
        {
            ScenarioContext.Current.Pending();
        }

        [Then(@"the post should have (.*) up vote")]
        public void ThenThePostShouldHaveUpVote(int p0)
        {
            ScenarioContext.Current.Pending();
        }

        [Then(@"the post should have (.*) down votes")]
        public void ThenThePostShouldHaveDownVotes(int p0)
        {
            ScenarioContext.Current.Pending();
        }
    }
}
