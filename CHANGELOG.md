# Changelog

## 1.0.0 (2024-10-16)


### Features

* add @webda/workout logger system (Close [#53](https://github.com/laursisask/repo-5/issues/53)) ([bba96db](https://github.com/laursisask/repo-5/commit/bba96db1b42a0e04283ee2446143cc567d0c86ac))
* add a log processor to output received email from smtp ([98f132c](https://github.com/laursisask/repo-5/commit/98f132cd12423042fab1bc2a87245e42b1030f16))
* add a static-auth filter ([b8b9bc6](https://github.com/laursisask/repo-5/commit/b8b9bc6c90e7425fb1365974700a78f9b8fb9a34))
* add auto shipit configuration ([638a64d](https://github.com/laursisask/repo-5/commit/638a64d316b0f60e3a166832d20a09cd57272722))
* add AWS and Nodemailer implementation ([63e93b9](https://github.com/laursisask/repo-5/commit/63e93b9c9a42926f7a4d9c1ee7bdd490dff39194))
* add basic whitelist filter ([e572fb7](https://github.com/laursisask/repo-5/commit/e572fb77bc7d5a6cd9e2bcf901c51c697af2b6c7))
* add cloudevent and gcp storage and pubsub ([0c01663](https://github.com/laursisask/repo-5/commit/0c01663e83d2259d917d1f2d275ee1d2d64bdb1f))
* add json schema for config file ([c71c586](https://github.com/laursisask/repo-5/commit/c71c5865fefa878ceaa19d551db8dffdd005270e))
* add jsonc capabilities ([476c948](https://github.com/laursisask/repo-5/commit/476c948f4a42bdb10fc93e086d952ada9828878e))
* add prometheus support (Close [#52](https://github.com/laursisask/repo-5/issues/52)) ([a16b0a8](https://github.com/laursisask/repo-5/commit/a16b0a89df02864e8cfda427d8c11de505c386ed))
* add verbose log (Close [#36](https://github.com/laursisask/repo-5/issues/36)) ([06ecc75](https://github.com/laursisask/repo-5/commit/06ecc75d6b886233a97d5e55b9ac4e46ee6f485e))
* add yaml support for configuration file (Close [#23](https://github.com/laursisask/repo-5/issues/23)) ([2479e76](https://github.com/laursisask/repo-5/commit/2479e76bd4c94cb274d4cac4beea9f702396b615))
* first commit ([6bbd6d7](https://github.com/laursisask/repo-5/commit/6bbd6d78e7f9b4def53e2331649ab2136f933483))
* move to ESM module ([b8ded3a](https://github.com/laursisask/repo-5/commit/b8ded3af0b6b688e12fe6df616e45917e1da327a))


### Bug Fixes

* add aws-sts library for service account (Close [#21](https://github.com/laursisask/repo-5/issues/21)) ([806e44e](https://github.com/laursisask/repo-5/commit/806e44e44885414a2409ecd4d7ab1d2f27e9e15f))
* add default CONSOLE logger ([dc58094](https://github.com/laursisask/repo-5/commit/dc58094e929aca8d35d2aced7d6c88b301daf3c7))
* add missing plugin ([e5dffe6](https://github.com/laursisask/repo-5/commit/e5dffe6b6d3ce51627d14438157d2d6ffee34723))
* allow usage of EKS service accounts ([e5e05f1](https://github.com/laursisask/repo-5/commit/e5e05f16b5434ca241ede6e2e18063c992f30920))
* docker builder ([98f919e](https://github.com/laursisask/repo-5/commit/98f919ebf0f0901d19be0048ce5670153b62e64e))
* Docker SIGINT handler ([ceec297](https://github.com/laursisask/repo-5/commit/ceec2971616813c6d7a704c2eee907098a0bf01e))
* prometheus coverage ([ed0548a](https://github.com/laursisask/repo-5/commit/ed0548aa9ce233b9b156788e0167b6988d425d48))
* try to trigger 0.1.1 release ([4a496c7](https://github.com/laursisask/repo-5/commit/4a496c7ff2507295b0687f3154ca67d1e0680885))
* update dependencies ([8b3ee37](https://github.com/laursisask/repo-5/commit/8b3ee37c22aca81ad80289bb4da126d16d9d6914))
* use deploykey instead of gh actions creds for push ([78cc65e](https://github.com/laursisask/repo-5/commit/78cc65e2662a32cb2d8ba51decb0181a8801fc86))
* use latest image of distroless/nodejs18-debian11 ([6c897e8](https://github.com/laursisask/repo-5/commit/6c897e8ddfa0df97f3abfc876ee9ed8b744187c7))
* use WorkerOutput instead of console.log ([d030903](https://github.com/laursisask/repo-5/commit/d0309036d592a61f2d82ba6a6c5d8ebb31eb587c))

## [1.3.1](https://github.com/loopingz/smtp-relay/compare/v1.3.0...v1.3.1) (2023-06-24)


### Bug Fixes

* use latest image of distroless/nodejs18-debian11 ([6c897e8](https://github.com/loopingz/smtp-relay/commit/6c897e8ddfa0df97f3abfc876ee9ed8b744187c7))
* use WorkerOutput instead of console.log ([d030903](https://github.com/loopingz/smtp-relay/commit/d0309036d592a61f2d82ba6a6c5d8ebb31eb587c))

## [1.3.0](https://github.com/loopingz/smtp-relay/compare/v1.2.2...v1.3.0) (2023-06-21)


### Features

* add a log processor to output received email from smtp ([98f132c](https://github.com/loopingz/smtp-relay/commit/98f132cd12423042fab1bc2a87245e42b1030f16))

## v1.2.2 (Thu Feb 02 2023)

#### 🐛 Bug Fix

- fix: update dependencies (remi@arize.com)

#### Authors: 1

- Remi Cattiau (remi@arize.com)

---

## v1.2.1 (Wed Jan 25 2023)

#### 🐛 Bug Fix

- fix: add default CONSOLE logger [#56](https://github.com/loopingz/smtp-relay/pull/56) ([@loopingz](https://github.com/loopingz))

#### Authors: 1

- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## v1.2.0 (Wed Jan 25 2023)

#### 🚀 Enhancement

- ci: update json schema prior to release [#55](https://github.com/loopingz/smtp-relay/pull/55) ([@loopingz](https://github.com/loopingz))
- feat: add verbose log (Close #36) [#55](https://github.com/loopingz/smtp-relay/pull/55) ([@loopingz](https://github.com/loopingz))
- feat: add json schema for config file [#55](https://github.com/loopingz/smtp-relay/pull/55) ([@loopingz](https://github.com/loopingz))
- feat: add prometheus support (Close #52) [#55](https://github.com/loopingz/smtp-relay/pull/55) ([@loopingz](https://github.com/loopingz))

#### 🐛 Bug Fix

- fix: prometheus coverage [#55](https://github.com/loopingz/smtp-relay/pull/55) ([@loopingz](https://github.com/loopingz))

#### Authors: 1

- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## v1.1.0 (Wed Jan 25 2023)

#### 🚀 Enhancement

- feat: add @webda/workout logger system (Close #53) [#54](https://github.com/loopingz/smtp-relay/pull/54) ([@loopingz](https://github.com/loopingz))

#### 🐛 Bug Fix

- chore: update dependabot settings and add small doc [#54](https://github.com/loopingz/smtp-relay/pull/54) ([@loopingz](https://github.com/loopingz))

#### 🔩 Dependency Updates

- build(deps): bump @aws-sdk/client-ses from 3.234.0 to 3.235.0 [#48](https://github.com/loopingz/smtp-relay/pull/48) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps): bump @aws-sdk/client-s3 from 3.234.0 to 3.235.0 [#49](https://github.com/loopingz/smtp-relay/pull/49) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps): bump @aws-sdk/client-sqs from 3.234.0 to 3.235.0 [#51](https://github.com/loopingz/smtp-relay/pull/51) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps): bump @aws-sdk/client-sqs from 3.231.0 to 3.234.0 [#44](https://github.com/loopingz/smtp-relay/pull/44) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps): bump @aws-sdk/client-s3 from 3.231.0 to 3.234.0 [#45](https://github.com/loopingz/smtp-relay/pull/45) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps-dev): bump esbuild from 0.16.9 to 0.16.10 [#46](https://github.com/loopingz/smtp-relay/pull/46) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps): bump @aws-sdk/client-ses from 3.231.0 to 3.234.0 [#47](https://github.com/loopingz/smtp-relay/pull/47) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps-dev): bump esbuild from 0.16.7 to 0.16.9 [#42](https://github.com/loopingz/smtp-relay/pull/42) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps-dev): bump prettier-plugin-organize-imports [#41](https://github.com/loopingz/smtp-relay/pull/41) ([@dependabot[bot]](https://github.com/dependabot[bot]))

#### Authors: 2

- [@dependabot[bot]](https://github.com/dependabot[bot])
- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## v1.0.2 (Fri Dec 16 2022)

#### 🐛 Bug Fix

- ci: publish version w/o release label [#40](https://github.com/loopingz/smtp-relay/pull/40) ([@loopingz](https://github.com/loopingz))
- fix: use deploykey instead of gh actions creds for push [#39](https://github.com/loopingz/smtp-relay/pull/39) ([@loopingz](https://github.com/loopingz))

#### Authors: 1

- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## v1.0.1 (Thu Dec 15 2022)

#### 🐛 Bug Fix

- fix: docker builder [#38](https://github.com/loopingz/smtp-relay/pull/38) ([@loopingz](https://github.com/loopingz))

#### Authors: 1

- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## v1.0.0 (Thu Dec 15 2022)

:tada: This release contains work from a new contributor! :tada:

Thank you, Remi Cattiau ([@loopingz](https://github.com/loopingz)), for all your work!

#### 💥 Breaking Change

- build(deps-dev): bump @types/mocha from 9.1.1 to 10.0.1 [#27](https://github.com/loopingz/smtp-relay/pull/27) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps-dev): bump sinon from 14.0.2 to 15.0.0 [#28](https://github.com/loopingz/smtp-relay/pull/28) ([@dependabot[bot]](https://github.com/dependabot[bot]))

#### 🚀 Enhancement

- fix: add missing plugin [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- feat: add auto shipit configuration [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add .env to gitignore [#31](https://github.com/loopingz/smtp-relay/pull/31) ([@loopingz](https://github.com/loopingz))
- feat: add yaml support for configuration file (Close #23) ([@loopingz](https://github.com/loopingz))
- feat: move to ESM module ([@loopingz](https://github.com/loopingz))
- feat: add a static-auth filter ([@loopingz](https://github.com/loopingz))
- build(deps-dev): bump @testdeck/mocha from 0.2.2 to 0.3.3 [#30](https://github.com/loopingz/smtp-relay/pull/30) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- feat: add AWS and Nodemailer implementation ([@loopingz](https://github.com/loopingz))
- feat: add cloudevent and gcp storage and pubsub ([@loopingz](https://github.com/loopingz))
- feat: add jsonc capabilities ([@loopingz](https://github.com/loopingz))
- feat: add basic whitelist filter ([@loopingz](https://github.com/loopingz))
- feat: first commit ([@loopingz](https://github.com/loopingz))

#### 🐛 Bug Fix

- ci: remove docker plugin for auto [#37](https://github.com/loopingz/smtp-relay/pull/37) ([@loopingz](https://github.com/loopingz))
- ci: build container on tag push [#37](https://github.com/loopingz/smtp-relay/pull/37) ([@loopingz](https://github.com/loopingz))
- fix: try to trigger 0.1.1 release [#37](https://github.com/loopingz/smtp-relay/pull/37) ([@loopingz](https://github.com/loopingz))
- ci: update workflows trigger [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: increase timeout for yarn install [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- fix: Docker SIGINT handler [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: test [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: remove canary deployment [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: use yarn.lock in ci [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add yarn.lock [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add workflow [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- fix: allow usage of EKS service accounts [#22](https://github.com/loopingz/smtp-relay/pull/22) ([@loopingz](https://github.com/loopingz))
- ci: split sonarcloud ci [#22](https://github.com/loopingz/smtp-relay/pull/22) ([@loopingz](https://github.com/loopingz))
- ci: disable stip-json-comments update as it enforce ESM [#5](https://github.com/loopingz/smtp-relay/pull/5) ([@loopingz](https://github.com/loopingz))
- Update README.md [#5](https://github.com/loopingz/smtp-relay/pull/5) ([@loopingz](https://github.com/loopingz))

#### ⚠️ Pushed to `main`

- docs: exclude dependabot ([@loopingz](https://github.com/loopingz))
- ci: add all-contributors ([@loopingz](https://github.com/loopingz))
- ci: add auto release manager ([@loopingz](https://github.com/loopingz))
- docs: improve README readability ([@loopingz](https://github.com/loopingz))
- ci: add auth test ([@loopingz](https://github.com/loopingz))
- ci: move to node16 and greater ([@loopingz](https://github.com/loopingz))
- refactor: use organize-imports ([@loopingz](https://github.com/loopingz))
- ci: remove cov on sonarcloud ([@loopingz](https://github.com/loopingz))
- refactor: sonar code smells ([@loopingz](https://github.com/loopingz))
- ci: add gcp test ([@loopingz](https://github.com/loopingz))
- docs: update sonarcloud badge ([@loopingz](https://github.com/loopingz))
- ci: add badges ([@loopingz](https://github.com/loopingz))
- ci: add more unit tests ([@loopingz](https://github.com/loopingz))
- refactor: move to SmtpComponent root class ([@loopingz](https://github.com/loopingz))
- ci: sonar update to project ([@loopingz](https://github.com/loopingz))
- ci: add missing sonar file ([@loopingz](https://github.com/loopingz))
- ci: add github files ([@loopingz](https://github.com/loopingz))
- ci: add basic workflows ([@loopingz](https://github.com/loopingz))
- v0.0.1 ([@loopingz](https://github.com/loopingz))

#### Authors: 2

- [@dependabot[bot]](https://github.com/dependabot[bot])
- Remi Cattiau ([@loopingz](https://github.com/loopingz))

---

## (Thu Dec 15 2022)

#### 💥 Breaking Change

- build(deps-dev): bump @types/mocha from 9.1.1 to 10.0.1 [#27](https://github.com/loopingz/smtp-relay/pull/27) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- build(deps-dev): bump sinon from 14.0.2 to 15.0.0 [#28](https://github.com/loopingz/smtp-relay/pull/28) ([@dependabot[bot]](https://github.com/dependabot[bot]))

#### 🚀 Enhancement

- fix: add missing plugin [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- feat: add auto shipit configuration [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add .env to gitignore [#31](https://github.com/loopingz/smtp-relay/pull/31) ([@loopingz](https://github.com/loopingz))
- feat: add yaml support for configuration file (Close #23) ([@loopingz](https://github.com/loopingz))
- feat: move to ESM module ([@loopingz](https://github.com/loopingz))
- feat: add a static-auth filter ([@loopingz](https://github.com/loopingz))
- build(deps-dev): bump @testdeck/mocha from 0.2.2 to 0.3.3 [#30](https://github.com/loopingz/smtp-relay/pull/30) ([@dependabot[bot]](https://github.com/dependabot[bot]))
- feat: add AWS and Nodemailer implementation ([@loopingz](https://github.com/loopingz))
- feat: add cloudevent and gcp storage and pubsub ([@loopingz](https://github.com/loopingz))
- feat: add jsonc capabilities ([@loopingz](https://github.com/loopingz))
- feat: add basic whitelist filter ([@loopingz](https://github.com/loopingz))
- feat: first commit ([@loopingz](https://github.com/loopingz))

#### 🐛 Bug Fix

- ci: update workflows trigger [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: increase timeout for yarn install [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- fix: Docker SIGINT handler [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: test [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: remove canary deployment [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: use yarn.lock in ci [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add yarn.lock [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- ci: add workflow [#34](https://github.com/loopingz/smtp-relay/pull/34) ([@loopingz](https://github.com/loopingz))
- fix: allow usage of EKS service accounts [#22](https://github.com/loopingz/smtp-relay/pull/22) ([@loopingz](https://github.com/loopingz))
- ci: split sonarcloud ci [#22](https://github.com/loopingz/smtp-relay/pull/22) ([@loopingz](https://github.com/loopingz))
- ci: disable stip-json-comments update as it enforce ESM [#5](https://github.com/loopingz/smtp-relay/pull/5) ([@loopingz](https://github.com/loopingz))
- Update README.md [#5](https://github.com/loopingz/smtp-relay/pull/5) ([@loopingz](https://github.com/loopingz))

#### ⚠️ Pushed to `main`

- docs: exclude dependabot ([@loopingz](https://github.com/loopingz))
- ci: add all-contributors ([@loopingz](https://github.com/loopingz))
- ci: add auto release manager ([@loopingz](https://github.com/loopingz))
- docs: improve README readability ([@loopingz](https://github.com/loopingz))
- ci: add auth test ([@loopingz](https://github.com/loopingz))
- ci: move to node16 and greater ([@loopingz](https://github.com/loopingz))
- refactor: use organize-imports ([@loopingz](https://github.com/loopingz))
- ci: remove cov on sonarcloud ([@loopingz](https://github.com/loopingz))
- refactor: sonar code smells ([@loopingz](https://github.com/loopingz))
- ci: add gcp test ([@loopingz](https://github.com/loopingz))
- docs: update sonarcloud badge ([@loopingz](https://github.com/loopingz))
- ci: add badges ([@loopingz](https://github.com/loopingz))
- ci: add more unit tests ([@loopingz](https://github.com/loopingz))
- refactor: move to SmtpComponent root class ([@loopingz](https://github.com/loopingz))
- ci: sonar update to project ([@loopingz](https://github.com/loopingz))
- ci: add missing sonar file ([@loopingz](https://github.com/loopingz))
- ci: add github files ([@loopingz](https://github.com/loopingz))
- ci: add basic workflows ([@loopingz](https://github.com/loopingz))
- v0.0.1 ([@loopingz](https://github.com/loopingz))

#### Authors: 2

- [@dependabot[bot]](https://github.com/dependabot[bot])
- Remi Cattiau ([@loopingz](https://github.com/loopingz))
