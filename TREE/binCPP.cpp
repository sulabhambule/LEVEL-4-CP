#include <bits/stdc++.h>
using namespace std;

const int MAX_LOG = 20;           // Maximum depth for binary lifting
const int N = 2e5 + 1;            // Maximum number of nodes
vector<int> adj[N];               // Adjacency list for the tree
int depth[N];                     // Depth of each node
int par[N][MAX_LOG];              // Binary lifting table

void dfs(int node, int parent) {
    depth[node] = depth[parent] + 1;
    par[node][0] = parent;        // Set the direct parent
    for (int j = 1; j < MAX_LOG; j++) {
        par[node][j] = par[par[node][j - 1]][j - 1];
    }
    for (int neighbor : adj[node]) {
        if (neighbor != parent) {
            dfs(neighbor, node);
        }
    }
}

int LCA(int nodeA, int nodeB) {
    if (nodeA == nodeB) return nodeA;

    // Make sure nodeA is deeper
    if (depth[nodeA] < depth[nodeB]) {
        swap(nodeA, nodeB);
    }

    // Bring nodeA and nodeB to the same depth
    int diff = depth[nodeA] - depth[nodeB];
    for (int j = MAX_LOG - 1; j >= 0; j--) {
        if ((diff >> j) & 1) {
            nodeA = par[nodeA][j];
        }
    }

    // If they are the same node after adjusting depth
    if (nodeA == nodeB) return nodeA;

    // Move both nodes up until their parents converge
    for (int j = MAX_LOG - 1; j >= 0; j--) {
        if (par[nodeA][j] != par[nodeB][j]) {
            nodeA = par[nodeA][j];
            nodeB = par[nodeB][j];
        }
    }

    // The parent of nodeA (or nodeB) is the LCA
    return par[nodeA][0];
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, q;
    cin >> n >> q;

    // Build the adjacency list
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    // Precompute binary lifting table
    dfs(1, 0);

    // Process queries
    while (q--) {
        int a, b;
        cin >> a >> b;
        int lca = LCA(a, b);
        int distance = depth[a] + depth[b] - 2 * depth[lca];
        cout << distance << '\n';
    }

    return 0;
}
