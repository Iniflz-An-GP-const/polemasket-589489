# Polemasket

This project was generated from a donor template by TemplateForge.
Application type: **Service**.

- Package: `retail.polemasket.digital`
- Project name: `Polemasket`

## Where the content lives

- `app/src/main/res/values/strings.xml` — English strings, the primary
  locale. `app/src/main/res/values-<lang>/strings.xml` — the same
  resources translated into de, es, fr, hi, id, pt and ar.
- The Kotlin data files sit in the `data` package of
  `retail.polemasket.digital`, under `app/src/main/java/`:
  - `data/model/ServiceCategory.kt` — the category enum. It ships
    empty; a value takes the string resource created for that category,
    never a literal, plus a Material icon matching it.
  - `data/repository/ServiceRepository.kt` — the service catalog. It
    ships empty. A service is shaped by `ServiceModel` in
    `data/model/ServiceModel.kt`.
  - `data/model/Benefit.kt` (`defaultServiceBenefits`) — short benefit
    statements shown on the details screen, each with an icon matching
    its text.
  - `data/model/Localized.kt` — `forCurrentLocale()`, the lookup every
    `texts` map is read through. Nothing to fill in here.

## Data invariants

- English is the primary language. `values/strings.xml` and the `"en"`
  entry of every `texts` map are in English, even when the task's input
  (e.g. the topic) is given in another language.
- A `texts` map is keyed by two-letter language code. The `"en"` entry
  is mandatory — it is the fallback and the source of the service name
  used to look up the photo; the other languages of this project follow
  it: "de", "es", "fr", "hi", "id", "pt", "ar". A service holds a `ServiceText` (name,
  description and the included items) per language; a benefit holds one
  string per language.
- `includedItems` is what the details screen lists under "What is
  included": short phrases, not paragraphs, and not a repeat of the
  description. A service with an empty list leaves that section blank
  on screen.
- `ServiceModel.id` values are unique, numbered from 1 upwards. The
  details screen looks a service up by its id.
- `imageUrl` holds a direct link to the image file: the app loads it
  straight into an image view. An unverified link is worse than none,
  so the field stays empty unless the URL was checked.
- Prices are realistic US-dollar amounts, charged per hour. The same
  number is shown in every locale — nothing is converted — so the
  symbol changes only where that stays truthful: `€` in the locales of
  eurozone countries (German, French), and `$` in every other locale of
  this project. A local symbol over an unconverted dollar amount —
  `₹`, `Rp`, `₺` — reads as a pricing error, not as a translation. The
  hourly unit is always translated (`/ Stunde`,
  `/ hora`), and the placement and the decimal separator follow the
  language.
- A new category resource is named
  `service_category_<category_name>_title`, and belongs under the
  `service categories` comment in `strings.xml`.
- A translation keeps the resource name and the placeholders of the
  English string (`%1$s`, `%2$d`, `%1$.2f`, ...) in the same number.
  Their order changes only if the language needs it, and then in
  positional form (`%2$s` before `%1$s`).
- A right-to-left language (Arabic, Hebrew, Persian, Urdu) reorders a
  run of digits and separators sitting inside its own text: `%1$d/%2$d`
  can reach the screen as `8/3`, and `#%1$s · %2$s` with its two parts
  swapped. Keep the placeholders in the order the English string gives
  them, and wrap each such run in left-to-right marks —
  `\u200E%1$d/%2$d\u200E` — which are invisible and change nothing else.
  It applies to every digit-and-symbol string: a price, a counter, a
  progress pair, anything joined by `#`, `/`, `×` or `·`.
- Resources marked `translatable="false"` — the app name, the version,
  the company name, the support link — stay exactly as they are and are
  not copied into `values-<lang>/`; neither are empty resources.
- In any `strings.xml`, escape an apostrophe as `\'` and an ampersand
  as `&amp;`. A bare apostrophe is still well-formed XML, so an XML
  check will not catch it — aapt2 will, by failing the build.

## Text has to fit the layout

- Rephrase a string resource when it reads as generic and
  topic-specific text would fit better. One that already reads
  naturally for the topic stays as it is — the goal is a cohesive
  result, not rewriting for its own sake. Pure interface chrome (Back,
  Next, Cancel, Done) has no topic to fit: leave it alone.
- A replacement keeps roughly the length of the text it replaces — the
  screens were built around those lengths, and longer text wraps badly
  or gets truncated on a phone. The same goes for a translation against
  its English original.
- Buttons, tabs, labels and titles stay short, usually one to three
  words: do not turn a label into a sentence, and do not add marketing
  filler, adjective chains, or the company name where the original had
  none.

## Working in this repo

- Keep changes scoped to what the current task asks for — this is a
  freshly generated project, not a hand-maintained one, so do not
  "clean up" unrelated files.
- Preserve the existing package structure and naming conventions found
  in the generated code instead of introducing new patterns.
- Do not modify build/gradle configuration, project name, package name,
  or app id — those were set intentionally during generation.
- Do not run Gradle builds, dependency downloads, Android lint, or
  emulator tests. The project is published as-is after your work.
  Validate only the modified data and resource files with lightweight
  local checks.
- Do not change business logic or UI code — limit changes strictly to
  the data described above (categories, string resources, translations,
  services, benefits). Creating the `values-<lang>/strings.xml` files
  is part of that data.
- When filling in placeholder data, match the style and format already
  used elsewhere in the file.
