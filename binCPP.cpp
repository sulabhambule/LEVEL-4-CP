#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9 + 7;
#define int long long

void dfs(int node, int parent, int depth, vector<vector<int>> &adj, vector<int> &subTreeSize, int &answer)
{
    subTreeSize[node] = 1; // Initialize the size of the subtree rooted at `node`
    answer += depth;       // Add the depth of the current node to the answer
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        { // Avoid traversing back to the parent
            dfs(adjNode, node, depth + 1, adj, subTreeSize, answer);
            subTreeSize[node] += subTreeSize[adjNode]; // Update subtree size
        }
    }
}

void reRoot(int node, int parent, vector<vector<int>> &adj, vector<int> &subTreeSize, int totalAnswer, vector<int> &answer)
{
    answer[node] = totalAnswer; // Store the result for the current node
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        { // Avoid traversing back to the parent
            // Calculate new answer when re-rooting to the adjacent node
            int newAnswer = totalAnswer - subTreeSize[adjNode] + (subTreeSize[1] - subTreeSize[adjNode]);
            reRoot(adjNode, node, adj, subTreeSize, newAnswer, answer);
        }
    }
}

void solve()
{
    int n;
    cin >> n;

    // Create adjacency list
    vector<vector<int>> adj(n + 1);
    for (int i = 0; i < n - 1; i++)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    vector<int> subTreeSize(n + 1, 0);
    vector<int> answer(n + 1, 0);

    // Calculate the total answer for the root (node 1) using DFS
    int totalAnswer = 0;
    dfs(1, -1, 0, adj, subTreeSize, totalAnswer);

    // Re-root the tree and calculate answers for all nodes
    reRoot(1, -1, adj, subTreeSize, totalAnswer, answer);

    // Print the results
    for (int i = 1; i <= n; i++)
    {
        cout << answer[i] << " ";
    }
    cout << endl;
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T = 1;
    while (T--)
    {
        solve();
    }

    return 0;
}
