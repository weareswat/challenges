namespace Api.Models
{
    public class Post
    {
        public int Id { get; set; }
        public int UpVotes { get; set; }
        public int DownVotes { get; set; }
        internal double Ratio
        {
            get
            {
                const int noVotes = 0;
                return UpVotes > noVotes || DownVotes > noVotes
                    ? (double)UpVotes / (UpVotes + DownVotes)
                    : noVotes;
            }
        }
        internal int DiffVotes
        {
            get
            {
                return UpVotes - DownVotes;
            }
        }
    }
}
